$(function () {
    changeBgImg();
    $(window).on("resize",function(){
        changeBgImg();
    });
});

function changeBgImg() {
    var winWidth = $(window).width();
    $("#carousel-qhkt > .carousel-inner > .item").each(function(index,el){
        var div = $(el);//获取到轮播图的每一个div
        var dataImg = (winWidth <= 768)?"xs-img":"lg-img";//根据宽度获取使用xs还是ls
        var bimgPath = div.data(dataImg);//拿到使用的图片路径
        if(winWidth <= 768){
            div.html("<img src=\""+bimgPath+"\" alt=\"carousel\">")
        }else{
            div.html("");
            div.css({background:"url("+bimgPath+") no-repeat"})
        }
    });
}