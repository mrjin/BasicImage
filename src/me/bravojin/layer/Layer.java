package me.bravojin.layer;

import com.sun.xml.internal.messaging.saaj.util.FinalArrayList;
import me.bravojin.effect.EffectInterface;
import me.bravojin.filter.FilterInterface;
import me.bravojin.filter.linear.FilterBoxSmooth;
import me.bravojin.filter.linear.FilterGuassSmooth;
import me.bravojin.filter.type.FilterType;
import me.bravojin.history.LayerHistory;
import me.bravojin.layer.gradient.GradientInterface;
import me.bravojin.layer.gradient.gradientType.GradientTypeEnumerator;
import me.bravojin.layer.gradient.lightingGradient.LightingGradientLinear;
import me.bravojin.layer.gradient.transparentGradient.TransparentGradientLinear;
import me.bravojin.layer.mask.MaskInterface;
import me.bravojin.zone.ZoneInterface;

import java.awt.image.BufferedImage;
import java.util.*;

/**
 * Created by tyrionlanister on 15-6-9.
 */
public class Layer implements LayerInterface{
    private ArrayList<GradientInterface> gradientArray;
    private ArrayList<MaskInterface> maskArray;
    private ArrayList<EffectInterface> effectArray;
    private ArrayList<FilterInterface> filterArray;

    private BufferedImage originImg;
    private LinkedList<BufferedImage> imgSave;
    //private ArrayList<BufferedImage> diffImgSave;
    private BufferedImage topLayerImg;

    private ZoneInterface zone;

    private BufferedImage histogram;
    private int index;
    private int save;
    //private static int effNum = 4;

    private LinkedList<LayerHistory> layerHistories;

    private ArrayList<GradientInterface> gradientArrayDelete;
    private ArrayList<MaskInterface> maskArrayDelete;
    private ArrayList<EffectInterface> effectArrayDelete;
    private ArrayList<FilterInterface> filterArrayDelete;


    public Layer(int index, int save){
        this.index = index;
        this.save = save;
        this.imgSave = new LinkedList<BufferedImage>();
        this.layerHistories = new LinkedList<LayerHistory>();
        //this.diffImgSave = new ArrayList<BufferedImage>(effNum);
    }
    public Layer(int index, BufferedImage originImg) {
        this.index = index;
        this.save = 5;
        this.imgSave = new LinkedList<BufferedImage>();
        this.layerHistories = new LinkedList<LayerHistory>();
        //this.diffImgSave = new ArrayList<BufferedImage>(effNum);
        this.originImg = originImg;
        this.topLayerImg = originImg;
        this.gradientArray = new ArrayList<GradientInterface>();
        this.effectArray = new ArrayList<EffectInterface>();
        this.maskArray = new ArrayList<MaskInterface>();
        this.filterArray = new FinalArrayList();
    }

    public Layer add(GradientInterface gradientInterface) {
        this.gradientArray.add(gradientInterface);
        if(this.originImg == null) {
            return this;
        }
        else if(this.topLayerImg == null) {
            this.updateTopLayer(gradientInterface.generate(this.originImg))
                    .updateLayerHistories("Gradient", this.gradientArray.size()-1, this.imgSave.size());
            return this;
        }
        else {
            this.updateImgSave();
            this.updateTopLayer(gradientInterface.generate(this.topLayerImg))
                    .updateLayerHistories("Gradient", this.gradientArray.size()-1, this.imgSave.size());
            return this;
        }
    }

    public Layer add(MaskInterface maskInterface) {
        this.maskArray.add(maskInterface);
        if(this.originImg == null) {
            return this;
        }
        else if(this.topLayerImg == null && this.originImg != null) {
            this.updateTopLayer(maskInterface.generate(this.originImg))
                    .updateLayerHistories("Mask", this.maskArray.size()-1, this.imgSave.size());
            return this;
        }
        else {
            this.updateImgSave();
            this.updateTopLayer(maskInterface.generate(this.topLayerImg))
                    .updateLayerHistories("Mask", this.maskArray.size()-1, this.imgSave.size());
            return this;
        }
    }

    public Layer add(FilterInterface filterInterface) {
        this.filterArray.add(filterInterface);
        if(this.originImg == null) {
            return this;
        }
        else if(this.topLayerImg == null && this.originImg != null) {
            this.updateTopLayer(filterInterface.generate(this.originImg))
                    .updateLayerHistories("Filter", this.filterArray.size()-1, this.imgSave.size());
            return this;
        }
        else {
            this.updateImgSave();
            this.updateTopLayer(filterInterface.generate(this.topLayerImg))
                    .updateLayerHistories("Filter", this.filterArray.size()-1, this.imgSave.size());
            return this;
        }
    }

    public Layer add(EffectInterface effectInterface) {
        this.effectArray.add(effectInterface);
        if(this.originImg == null) {
            return this;
        }
        else if(this.topLayerImg == null && this.originImg != null) {
            this.updateTopLayer(effectInterface.generate(this.originImg))
                    .updateLayerHistories("Effect", this.effectArray.size()-1, this.imgSave.size());
            return this;
        }
        else {
            this.updateImgSave();
            this.updateTopLayer(effectInterface.generate(this.topLayerImg))
                    .updateLayerHistories("Effect", this.effectArray.size()-1, this.imgSave.size());
            return this;
        }
    }

    public Layer delete(String name, int index) {
        int find = this.isInHistory(name, index);
        if(find != -1) {
            LayerHistory layerHistory = this.layerHistories.get(find);
            this.replaceTopLayerByIndex(layerHistory.getTopLayerIndex());
        }
        else {
            this.deleteByNameIndex(name, index).reGenerate();
        }
        return this;
    }

    private int isInHistory(String name, int index) {
        LayerHistory layerHistory = new LayerHistory(name, index);
        for(int i = 0; i < this.layerHistories.size(); i++) {
            if(this.layerHistories.get(i).isSame(layerHistory)) {
                return i;
            }
        }
        return -1;
    }

    private Layer deleteByNameIndex(String name, int index) {
        if(name == "Gradient" && index < this.gradientArray.size()) {
            this.gradientArrayDelete.add(this.gradientArray.get(index));
            this.gradientArray.remove(index);
            this.updateLayerHistories("Delete_Gradient",this.gradientArrayDelete.size()-1, this.imgSave.size()-1);
        }
        else if(name == "Mask" && index < this.maskArray.size()) {
            this.maskArrayDelete.add(this.maskArray.get(index));
            this.maskArray.remove(index);
            this.updateLayerHistories("Delete_Mask",this.maskArrayDelete.size()-1, this.imgSave.size()-1);
        }
        else if(name == "Effect" && index < this.effectArray.size()) {
            this.effectArrayDelete.add(this.effectArray.get(index));
            this.effectArray.remove(index);
            this.updateLayerHistories("Delete_Effect",this.effectArrayDelete.size()-1,this.imgSave.size()-1);
        }
        else if(name == "Filter" && index < this.filterArray.size()) {
            this.filterArrayDelete.add(this.filterArray.get(index));
            this.filterArray.remove(index);
            this.updateLayerHistories("Delete_Effect",this.filterArrayDelete.size()-1, this.imgSave.size()-1);
        }
        return this;
    }

    public Layer cancelLastDelete() {
        if(this.layerHistories.size() == 0) {
            return this;
        }
        else {
            LayerHistory layerHistory = this.layerHistories.get(this.layerHistories.size() - 1);
            this.addByHistory(layerHistory).replaceTopLayerByIndex(this.imgSave.size()-1);
            this.layerHistories.remove(this.layerHistories.size()-1);
            return this;
        }
    }

    private Layer addByHistory(LayerHistory layerHistory) {
        if(layerHistory.getClassName() == "Delete_Gradient") {
            if(layerHistory.getClassIndex() < this.gradientArrayDelete.size()) {
                this.gradientArray.add(this.gradientArrayDelete.get(layerHistory.getClassIndex()));
                this.gradientArrayDelete.remove(layerHistory.getClassIndex());
                this.updateLayerHistories("Gradient", this.gradientArray.size() - 1, this.imgSave.size() - 1);
            }
            return this;
        }
        else if(layerHistory.getClassName() == "Delete_Effect") {
            if(layerHistory.getClassIndex() < this.effectArrayDelete.size()) {
                this.effectArray.add(this.effectArrayDelete.get(layerHistory.getClassIndex()));
                this.effectArrayDelete.remove(layerHistory.getClassIndex());
                this.updateLayerHistories("Effect", this.effectArray.size() - 1, this.imgSave.size() - 1);
            }
            return this;
        }
        else if(layerHistory.getClassName() == "Delete_Mask") {
            if(layerHistory.getClassIndex() < this.maskArrayDelete.size()) {
                this.maskArray.add(this.maskArrayDelete.get(layerHistory.getClassIndex()));
                this.maskArrayDelete.remove(layerHistory.getClassIndex());
                this.updateLayerHistories("Mask", this.maskArray.size() - 1, this.imgSave.size() - 1);
            }
            return this;
        }
        else if(layerHistory.getClassIndex() < this.filterArrayDelete.size()) {
            if(layerHistory.getClassIndex() < this.maskArrayDelete.size()) {
                this.filterArray.add(this.filterArrayDelete.get(layerHistory.getClassIndex()));
                this.filterArrayDelete.remove(layerHistory.getClassIndex());
                this.updateLayerHistories("Filter", this.filterArray.size() - 1, this.imgSave.size() - 1);
            }
            return this;
        }
        return this;
    }

    public Layer cancelLastAdd() {
        if(this.layerHistories.size() == 0) {
            return this;
        }
        else {
            LayerHistory layerHistory = this.layerHistories.get(this.layerHistories.size() - 1);
            if(layerHistory == null) {
                System.out.print("[debug]200");
                return this;
            }
            this.removeByHistory(layerHistory);
            this.replaceTopLayerByIndex(this.imgSave.size()-1);
            this.layerHistories.remove(this.layerHistories.size()-1);
            return this;
        }
    }

    private Layer replaceTopLayerByIndex(int index) {
        if(index >= this.imgSave.size()) {
            return this;
        }
        else {
            BufferedImage img = this.topLayerImg;
            this.topLayerImg = this.imgSave.get(index);
            for(int i = index; i < this.imgSave.size(); i++) {
                this.imgSave.remove(i);
            }
            this.checkLayerHistories(this.save).reindexLayerHistories(index);
            this.imgSave.push(img);
            return this;
        }
    }

    private Layer removeByHistory(LayerHistory layerHistory){
        if(layerHistory.getClassName() == "Gradient") {
            this.gradientArrayDelete.add(this.gradientArray.get(layerHistory.getClassIndex()));
            this.updateLayerHistories("Delete_Gradient", layerHistory.getClassIndex(), this.imgSave.size() - 1);
            this.gradientArray.remove(layerHistory.getClassIndex());
            return this;
        }
        else if(layerHistory.getClassName() == "Effect"){
            this.effectArrayDelete.add(this.effectArray.get(layerHistory.getClassIndex()));
            this.updateLayerHistories("Delete_Effect", layerHistory.getClassIndex(), this.imgSave.size() - 1);
            this.effectArray.remove(layerHistory.getClassIndex());
            return this;
        }
        else if(layerHistory.getClassName() == "Mask") {
            this.maskArrayDelete.add(this.maskArray.get(layerHistory.getClassIndex()));
            this.updateLayerHistories("Delete_Mask", layerHistory.getClassIndex(), this.imgSave.size() - 1);
            this.maskArray.remove(layerHistory.getClassIndex());
            return this;
        }
        else if(layerHistory.getClassName() == "Filter") {
            this.filterArray.add(this.filterArray.get(layerHistory.getClassIndex()));
            this.updateLayerHistories("Delete_Filter", layerHistory.getClassIndex(), this.imgSave.size() - 1);
            this.filterArray.remove(layerHistory.getClassIndex());
            return this;
        }
        return this;
    }

    public Layer reGenerate() {
        BufferedImage resultImg = this.originImg;
        for(int i = 0; i < this.gradientArray.size(); i++) {
            resultImg = this.gradientArray.get(i).generate(resultImg);
        }
        for(int i = 0; i < this.maskArray.size(); i++) {
            resultImg = this.maskArray.get(i).generate(resultImg);
        }
        for(int i = 0; i < this.effectArray.size(); i++) {
            resultImg = this.effectArray.get(i).generate(resultImg);
        }
        for(int i = 0; i < this.filterArray.size(); i++) {
            resultImg = this.filterArray.get(i).generate(resultImg);
        }
        this.updateImgSave().updateTopLayer(resultImg).updateLayerHistories("Recreate",0,this.imgSave.size()-1);
        return this;
    }


    private Layer updateTopLayer(BufferedImage topLayerImg) {
        this.topLayerImg = topLayerImg;
        return this;
    }

    private Layer updateImgSave() {
        if(this.imgSave.size() == this.save) {
            this.imgSave.push(this.topLayerImg);
            this.imgSave.pop();
            this.checkLayerHistories(this.save).reindexLayerHistories(1);
        }
        else {
            this.imgSave.push(this.topLayerImg);
        }
        return this;
    }

    private Layer checkLayerHistories(int times) {
        if(this.layerHistories.size() > 2*times) {
            for(int i = 0; i < this.layerHistories.size(); i++) {
                if(this.layerHistories.size() > 2*times) {
                    this.layerHistories.pop();
                }
                else {
                    break;
                }
            }
        }
        return this;
    }

    private Layer reindexLayerHistories(int indexStep) {
        for(int i = 0 ; i < this.layerHistories.size(); i++) {
            int topLayerIndex = this.layerHistories.get(i).getTopLayerIndex();
            if(topLayerIndex < indexStep) {
                this.layerHistories.remove(i);
            }
            else {
                this.layerHistories.get(i).setTopLayerIndex(topLayerIndex - indexStep);
            }
        }
        return this;
    }

    private Layer updateLayerHistories(String name, int index, int topLayerIndex) {
        this.layerHistories.add(new LayerHistory(name, index, topLayerIndex));
        return this;
    }

    public BufferedImage getTopLayerImg()
    {
        return this.topLayerImg;
    }

    public void testHistories(int m) {
        System.out.println(m + ":");
        for(int i = 0 ; i < this.layerHistories.size(); i++) {
            System.out.println(this.layerHistories.get(i).toString());
        }
    }

    public int getFilterSize() {
        return this.filterArray.size();
    }

    public ArrayList<String> getGradient() {
        ArrayList<String> gradient = new ArrayList<String>();
        for(int i = 0; i < this.gradientArray.size(); i++) {
            GradientInterface gradientInterface = gradientArray.get(i);
            if(gradientInterface.getGradientType() == GradientTypeEnumerator.transparentGradient) {
                String s = new String();
                s += "Gradient:Transparent ";
                s += ((TransparentGradientLinear) gradientInterface).getStartPixel().toString() + " ";
                s += ((TransparentGradientLinear) gradientInterface).getEndPixel().toString() + " ";
                s += ((TransparentGradientLinear) gradientInterface).getDirectionConfig() + " ";
                s += ((TransparentGradientLinear) gradientInterface).getLevel();
                gradient.add(s);
            }
            else if(gradientInterface.getGradientType() == GradientTypeEnumerator.lightingGradient) {
                String s = new String();
                s += "Lighting:Gradient ";
                s += ((LightingGradientLinear) gradientInterface).getStartPixel().toString() + " ";
                s += ((LightingGradientLinear) gradientInterface).getEndPixel().toString() + " ";
                s += ((LightingGradientLinear) gradientInterface).getDirectionConfig() + " ";
                s += ((LightingGradientLinear) gradientInterface).getLevel();
                gradient.add(s);
            }
        }
        return gradient;
    }

    public ArrayList<String> getMask() {
        return new ArrayList<String>();
    }

    public ArrayList<String> getFilter() {
        ArrayList<String> filter = new ArrayList<String>();
        for(int i = 0; i < filterArray.size(); i++) {
            FilterInterface filterInterface = filterArray.get(i);
            if(filterInterface.getFilterType() == FilterType.BoxSmooth) {
                String s = "Filter:Box Smooth ";
                s += ((FilterBoxSmooth) filterInterface).toString() + " ";
                filter.add(s);
            }
            else if(filterInterface.getFilterType() == FilterType.GuassSmooth) {
                String s = "Filter:Guass ";
                s += ((FilterGuassSmooth) filterInterface).toString() + " ";
                filter.add(s);
            }
        }

        return filter;
    }

    public ArrayList<String> getParam() {
        return new ArrayList<String>();
    }
}
