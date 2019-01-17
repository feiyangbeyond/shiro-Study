$(function () {
    $(".collapse_all").on("shown.bs.collapse", function () {
        var a = $(this).prev();//获取到上一个兄弟节点对象
        var i = a.children();
        i.removeClass("icon-jia1");
        i.addClass("icon-hengxian1");
    });

    $(".collapse_all").on("hidden.bs.collapse", function () {
        var a = $(this).prev();//获取到上一个兄弟节点对象
        var i = a.children();
        i.removeClass("icon-hengxian1");
        i.addClass("icon-jia1");
    });

    $(".collapse_all > li > a").click(function (el) {
        el.preventDefault();//禁用a标签原本的功能
        var $this = $(this);
        $(".collapse_all > li > a").removeClass("navactive");
        $this.addClass("navactive");
        $("#iframe-contant").attr('src',$this.data("iframesrc"));
        //更新主显--路径导航
        var text = $this.text();
        var mnav = $this.parent().parent().prev().text();
        $("#path_nav > .breadcrumb >li:last-child").html(text);
        $("#path_nav > .breadcrumb >li:last-child").prev().html(mnav);
    });
});