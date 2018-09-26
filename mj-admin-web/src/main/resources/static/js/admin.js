(function(main){
    var element = layui.element;
    var slideBarClosed = false;
    /**
     *  切换tab 标签时选中菜单对应项
     */
    element.on('tab(tab-switch)', function(data){
        if(data.elem.context.attributes != undefined){
            var id = data.elem.context.attributes[0].nodeValue;
            layui.each($(".layui-nav-child"), function () {
                $(this).find("dd").removeClass("layui-this");
            });
            $("#"+id).attr("class","layui-this");
        }
    });

    /**
     * 增加左侧菜单可隐藏
     */
    $('#animation-left-nav').click(function(){
        if(!slideBarClosed){
            $(".layui-side").animate({width:'toggle'});
            $(".layui-body").animate({left:'0px'});
            $(".layui-footer").animate({left:'0px'});
        }else{
            $(".layui-body").animate({left:'200px'});
            $(".layui-footer").animate({left:'200px'});
            $(".layui-side").animate({width:'toggle'});
        }
        slideBarClosed = !slideBarClosed;
    });

    main.addTab = function(id,title,url){
        if ($(".layui-tab-title li[lay-id=" + id + "]").length > 0) {
            element.tabChange('tab-switch', id);
        }else{
            element.tabAdd('tab-switch', {
                title: title
                ,content: '<iframe src='+url+' width="100%" style="min-height: 350px;" ' +
                'frameborder="0" onload="setIframeHeight(this)" scrolling="auto"></iframe>' // 选项卡内容，支持传入html
                ,id: id
            });
            element.tabChange('tab-switch', id); //切换到新增的tab上
        }
    };

    main.setIframeHeight = function(iframe) {
        if (iframe) {
            var iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
            if (iframeWin.document.body) {
                iframe.height = iframeWin.document.documentElement.scrollHeight || iframeWin.document.body.scrollHeight;
            }
        }
    }


    main.tips = function(type,content,duration){
        var bgClass = type === 1 ? "layui-bg-green":type === 0 ? "layui-bg-red":"layui-bg-gray";
        content = content || "成功";
        duration = duration || 2200;
        var index = layer.open({
            type: 1
            ,title: false //不显示标题栏
            ,closeBtn: false
            ,area: '300px;'
            ,offset:'t'
            ,shade: 0.1
            ,id: 'LAY_layuiTips' //设定一个id，防止重复弹出
            ,resize: false
            ,moveType: 1 //拖拽模式，0或者1
            ,content: '<div class="'+bgClass+'" style="padding: 30px; line-height: 30px;font-weight: 300;text-align:center">'+content+'</div>'
            ,success: function(layero){
                setTimeout(function(){layer.close(index)},duration);
            }
        });

    }
})(window);