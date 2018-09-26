/**
 * Created by LELK on 2017/5/3.
 */
'use strict';

$.fn.videoCt = function (options) {
   
    //设置默认值
    var defaults = {
        title: '',
        volume: 0.2,
        barrage: false,
        comment: false,
        reversal: false,
        playSpeed: false,
        update: false,
        autoplay: false,
        clarity: {
            type: [],
            src: []
        },
        theme: 'blue',
        commentFile: 'comment.json',
        playNumber:'0'
    };
    var opts = $.extend({},defaults, options);
    var intOpts = {
        title: '',
        status: false,
        currentTime: '0.00001',
        duration: '',
        volume: '',
        clarityType: '',
        claritySrc: '',
        fullScreen: false,
        reversal: false,
        playSpeed: 1,
        cutover: true,
        commentTitle: '',
        commentId: '',
        commentClass: '',
        comment: {},
        commentSwitch: true,
        playNumber:'0'
    };

    // 控件结构
    var $videoCt = $(this);
    var $videoCtBox = $('<div></div>').addClass('video-player').addClass(opts.theme);
    var $videoCtControls = $('<div class="video-controls">\
                                <!--进度条-->\
                                <div class="video-seek">\
                                    <div class="seek-buffer"></div>\
                                    <div class="seek-con"></div>\
                                    <div class="seek-slider"></div>\
                                    <div class="viewBox">\
                                        <video></video>\
                                        <span>00:00</span>\
                                        <i></i>\
                                    </div>\
                                </div>\
                                <!--播放/暂停-->\
                                <a class="video-play" title="Play/Pause"></a>\
                                <!--计时器-->\
                                <div class="video-timer">\
                                    <span class="realTime">00:00</span>\
                                    /&nbsp;<span>00:00</span>\
                                </div>\
                                <!--播放次数-->\
                                <div class="video-times">\
                                    <div class="video-times-icon"></div>\
                                    <span>'+opts.playNumber+'</span><span>次播放</span>\
                                </div>\
                            </div>');
    $videoCt.wrap($videoCtBox);
    $videoCt.after($videoCtControls);
    $videoCt.after($('<div class="video-prompt">视频加载中。。。</div>'));
    videoDom(opts);

    var getVideoTimer;
    var comStayTimer = [];
    var comTimer = 0;
    var comStroeStatus = 0;
    var $video_container = $videoCt.parent('.video-player');
    var $videoCt_play = $('.video-play', $video_container);
    var $videoCt_seek = $('.video-seek', $video_container);
    var $videoCt_timer = $('.video-timer', $video_container);
    var $videoCt_audio = $('.video-audio', $video_container);
    var $videoCt_clarity = $('.video-clarity', $video_container);
    var $videoCt_screen = $('.video-screen', $video_container);

    $videoCt.attr('src',opts.clarity.src[0]);
    $('.video-seek video').attr('src', opts.clarity.src[0]);
    intOpts.clarityType = opts.clarity.type[0];
    intOpts.claritySrc = opts.clarity.src[0];
    $videoCt_clarity.find('span').eq(0).addClass('disabled');

    var getVideoInf = function (){
        var setTimer;
        var key = true;
        if ($videoCt[0].readyState != 4 && $('.video-seek video')[0].readyState != 4) {
            $('.video-prompt').fadeOut();
            getVideoTimer = setTimeout(getVideoInf, 100);
        } else {
            $('.video-prompt').fadeOut();

            //视频播放状态
            if (intOpts.status) {
                $videoCt[0].play();
            }
            //进度条/暂停播放
            if (!intOpts.currentTime) {
                intOpts.currentTime = $videoCt[0].currentTime;
            }
            $videoCt[0].currentTime = intOpts.currentTime;
            if (!intOpts.duration) {
                intOpts.duration = $videoCt[0].duration;
            }
            move($('.seek-slider'), $videoCt_seek, $('.seek-con'), 8, videoJump, $('.realTime'), true);
            $('.seek-slider').css('left', intOpts.currentTime / intOpts.duration * $('.video-seek').width() - 8);
            $('.seek-con').css('width', intOpts.currentTime / intOpts.duration * $('.video-seek').width());
            $('.video-seek').hover(function () {
                key = true;
                $(document).mousemove(function(e){
                    clearTimeout(setTimer);
                    if(key){hoverPlay(e);}
                });
            },function () {
                key = false;
                clearTimeout(setTimer);
                $('.video-seek .viewBox').fadeOut();
            });

            //时间
            $videoCt_timer.find('span').eq(0).text(gTimeFormat(intOpts.currentTime));
            $videoCt_timer.find('span').eq(1).text(gTimeFormat(intOpts.duration));

            //声音
            if (!intOpts.volume) {
                intOpts.volume = opts.volume;
            }
            $videoCt[0].volume = intOpts.volume;
            $videoCt_audio.find('.audio-button').removeClass('audio-mute');
            move($('.audio-slider'), $('.audio-box'), $('.audio-con'), 6, audioJump);
            $('.audio-slider').css('left', intOpts.volume * $('.audio-box').width() - 6);
            $('.audio-con').css('width', intOpts.volume * $('.audio-box').width());
            $videoCt_audio.find('.audio-button').off('click').on('click', function () {
                $(this).toggleClass('audio-mute');
                if ($videoCt[0].volume == 0) {
                    $videoCt[0].volume = intOpts.volume;
                } else {
                    $videoCt[0].volume = 0;
                }
            })


            //播放速度
            $('.video-playSpeed').off('click').on('click',function () {
                var value = parseFloat($(this).text().split('x').join(""));
                if(value >= 3){
                    value = 0;
                }
                value = value + 0.5;
                $videoCt[0].playbackRate = value;
                intOpts.playSpeed = value;
                $(this).text(value + 'x');
            });

            //清晰度
            $videoCt_clarity.find('span').off("click").on('click', function () {
                var ind = $(this).index();
                $videoCt_clarity.find('span').removeClass('disabled');
                $(this).addClass('disabled');
                $videoCt_clarity.find('p').text($(this).text());
                intOpts.clarityType = opts.clarity.type[ind];
                intOpts.claritySrc = opts.clarity.src[ind];
                $videoCt.attr('src', intOpts.claritySrc);
                $('.video-seek video').attr('src', intOpts.claritySrc);
                opts.autoplay = true;
                intOpts.cutover = false;
                $videoCt[0].currentTime = intOpts.currentTime;
                getVideoInf();
            })

            //下载
            $('.video-update').attr('download',intOpts.title);
            $('.video-update').attr('href',intOpts.claritySrc);

            //全屏
            $videoCt_screen.off('click').on('click', function () {
                if (intOpts.fullScreen == true) {
                    exitFullscreen();
                    $('body').removeClass('fullScreen');
                    intOpts.fullScreen = false;
                } else {
                    requestFullScreen();
                    $('body').addClass('fullScreen');
                    intOpts.fullScreen = true;
                }
                $('.seek-slider').css('left', intOpts.currentTime / intOpts.duration * $('.video-seek').width() - 8);
                $('.seek-con').css('width', intOpts.currentTime / intOpts.duration * $('.video-seek').width());
            })

        }
    }
    getVideoInf();

    //slider
    function move(slider, box, con, radius, fn, vBox, vFollow) {
        var vBox = vBox || false;
        var vFollow = vFollow || false;
        var moveStatus = false;
        var sL = slider.offset().left;
        slider.click(function(){
        }).mousedown(function(e){
            moveStatus = true;
            sL = e.pageX - parseInt(slider.css('left'));
        })
        $(document).mousemove(function(e){
            if(moveStatus){
                var diffL = e.pageX - sL;
                show(diffL);
            }
        }).mouseup(function(){
            moveStatus = false;
        });
        box.off('click').on('click',function (e) {
            var diffL = e.pageX - box.offset().left - radius;
            show(diffL);
        });
        function show(x) {
            if( x >= -1*radius && x <= parseInt(box.width() - radius)){
                slider.css('left',x);
                con.css('width',x+radius);
                if(vBox){
                    var fnIndex = $videoCt[0].duration * (x+radius)/box.width();
                    intOpts.currentTime = fnIndex;
                    comClear();
                    vBox.text(gTimeFormat(fnIndex));
                }else{
                    var fnIndex = (x+radius)/box.width();
                }
                fn(fnIndex);
            }
        }
        if(vFollow){
            //暂停播放
            var seTimer;
            var vPlay = function() {
                if(!intOpts.status) {
                    $videoCt[0].play();
                    intOpts.status = true;
                    //视频播放5秒提示付费
                    setTimeout(function(){
                        $videoCt[0].pause();
                        intOpts.status = false;
                       $('.tips').show();//弹出遮罩
                    },6000);
                    seTimer = setInterval(function () {
                        var vprpo = $videoCt[0].currentTime/$videoCt[0].duration;
                        if(intOpts.cutover){
                            var vBTime = $videoCt[0].buffered.end(0)/$videoCt[0].duration;
                        }
                        if( vprpo < 1){
                            var tl = vprpo * (box.width() - radius);
                            var bL = vBTime * box.width();
                            intOpts.currentTime = $videoCt[0].currentTime;
                            vBox.text(gTimeFormat($videoCt[0].currentTime));
                            slider.css('left',tl);
                            con.css('width',tl+radius);
                            $('.seek-buffer').css('width',bL);
                            intStatus();
                        }else if(vprpo == 1){
                            clearInterval(seTimer);
                            intOpts.status = false;
                            return true;
                        }else{
                            clearInterval(seTimer);
                            intOpts.status = true;
                            return true;
                        }
                    },10);
                } else {
                    $videoCt[0].pause();
                    intOpts.status = false;
                     comStop();
                    clearInterval(seTimer);
                }
            };

            if((intOpts.status || intOpts.cutover) && opts.autoplay){
                intOpts.status = false;
                vPlay();
            }

            $videoCt_play.removeClass('video-pause');
            $videoCt_play.off('click').on('click',vPlay);
            $videoCt.off('click').on('click',vPlay);

            $videoCt.bind('play', function() {
                $videoCt_play.addClass('video-pause');
            });

            $videoCt.bind('pause', function() {
                $videoCt_play.removeClass('video-pause');
            });

            $videoCt.bind('ended', function() {
                $videoCt_play.removeClass('video-pause');
            });
        }
    }

    //时间格式化
    var gTimeFormat = function (seconds) {
        var m = Math.floor(seconds / 60) < 10 ? "0" + Math.floor(seconds / 60) : Math.floor(seconds / 60);
        var s = Math.floor(seconds - (m * 60)) < 10 ? "0" + Math.floor(seconds - (m * 60)) : Math.floor(seconds - (m * 60));
        return m + ":" + s;
    };

    //进度条显示
    function hoverPlay(e) {
        var view = e.pageX - $('.video-seek').offset().left + 1;
        var time = $videoCt[0].duration * view / $('.video-seek').width();
        if(view <= 30){
            $('.video-seek .viewBox').css('left',-20);
            $('.video-seek .viewBox i').css('left',view+15);
        }else if(view >= ($('.video-seek').width()-30)){
            $('.video-seek .viewBox').css('left',$('.video-seek').width()-80);
            $('.video-seek .viewBox i').css('left',view-$('.video-seek').width()+75);
        }else{
            $('.video-seek .viewBox').css('left',view - 50);
            $('.video-seek .viewBox i').css('left',44);
        }
        $('.video-seek video')[0].currentTime = time;
        $('.video-seek span').text(gTimeFormat(time));
        $('.video-seek .viewBox').fadeIn();
    }

    //视频跳转
    function videoJump( timer ) {
        $videoCt[0].currentTime = timer;
    }

    //视频网络卡端
    function intStatus(){
        if($videoCt[0].readyState == 4){
            $('.video-status').fadeOut();
        }else{
            $('.video-status').fadeIn();
            comStop();
        }
    }

    //音量调节
    function audioJump( volume ) {
        opts.volume = volume;
        $videoCt[0].volume = volume;
    }
  //弹幕停止
    function comStop() {
        $(".screenShoot[data-status='1']").each(function () {
            var that = $(this);
            var i = that.index();
            if(that.data('action') == 'marquee'){
                that.stop();
            }
            clearTimeout(comStayTimer[i]);
            that.attr('data-status',2);
        });
    }
    //弹幕清除
    function comClear() {
        $(".screenShoot[data-status='1']").each(function () {
            var that = $(this);
            var i = that.index();
            clearTimeout(comStayTimer[i]);
            if(that.data('action') == 'marquee'){
                $(this).stop();
                $(this).css('left', 110 + '%');
            }
            $(this).fadeOut();
            that.attr('data-status',0);
        });
    }


    //进入全屏
    function requestFullScreen() {
        var de = document.documentElement;
        if (de.requestFullscreen) {
            de.requestFullscreen();
        } else if (de.mozRequestFullScreen) {
            de.mozRequestFullScreen();
        } else if (de.webkitRequestFullScreen) {
            de.webkitRequestFullScreen();
        }
    }

    //退出全屏
    function exitFullscreen() {
        var de = document;
        if (de.exitFullscreen) {
            de.exitFullscreen();
        } else if (de.mozCancelFullScreen) {
            de.mozCancelFullScreen();
        } else if (de.webkitCancelFullScreen) {
            de.webkitCancelFullScreen();
        }
    }

    //扩展功能
    function videoDom( opts ) {
        // var playSpeed = $('<!--播放速度--><div class="video-playSpeed">1x</div>');
        var update = $('<!--下载--><a class="video-update"></a>');
        if( opts.title ){$videoCt.after(title)}
        // if( opts.playSpeed ){playSpeed.appendTo($(".video-controls"))}
        if( opts.update ){update.appendTo($(".video-controls"))}
        if( opts.clarity.type && opts.clarity.src ){
            var str = '';
            for(var i = 0;i < opts.clarity.type.length;i++){
                str = str + '<span>' + opts.clarity.type[i] + '</span>';
            }
            var clarity = '<!--清晰度--><div class="video-clarity"><p>'+ opts.clarity.type[0] +'</p><nav>'+ str +'</nav></div>';
            $(clarity).appendTo($(".video-controls"));
        }
    }

    return {
        title: '',
        status: false,
        currentTime: '0.00001',
        duration: '',
        volume: '',
        clarityType: '',
        claritySrc: '',
        fullScreen: false,
        reversal: false,
        playSpeed: 1,
        cutover: true,
        commentTitle: '',
        commentId: '',
        commentClass: '',
        commentSwitch: true,
        comment: function () {
            return intOpts.comment;
        }
    }
}
