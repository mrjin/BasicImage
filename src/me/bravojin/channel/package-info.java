/**这个包是为了给顶层的图片生成不同的通道的算法封装，一般来说，一张图片包含R、G、B三个颜色通道和灰度通道，因此会根据原图的像素点的取值，来分别生成三通道的灰度图和原图的灰度图。其中会有generate方法来通过输入的参数判断输入的图的类型。
 */
package me.bravojin.channel;