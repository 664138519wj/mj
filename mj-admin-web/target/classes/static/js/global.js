// 返回顶部
function goTop() {
    var gotop = "<img class='bottom-to-top' src='../images/scroll-to-top-icon.png' >";
    if ($('.bottom-to-top').length == 0) {
        $('body').append(gotop);
    }
    $(".bottom-to-top").hide();
    $(window).scroll(function () {
        if ($(window).scrollTop() > 600) {
            $(".bottom-to-top").show();
        }
        else {
            $(".bottom-to-top").hide();
        }
    });
    $(".bottom-to-top").click(function () {
        $('body,html').stop().animate({ scrollTop: 0 }, 500, function () {
            $('body,html').stop();
        });
        $(".bottom-to-top").hide();

    });
}
//顶部隐藏菜单点击事件
function menuClick() {
    var menuBtn=$('.switcher');
    var rNav=$('.nav-right');
    var sayHello=$('.say_hello');
    menuBtn.on('click',function () {
        if($(this).hasClass('open')){
            $(this).removeClass('open');
            rNav.addClass('nav-hide').removeClass('nav-show');
            sayHello.addClass('nav-hide').removeClass('nav-show')
        }else{
            $(this).addClass('open');
            rNav.removeClass('nav-hide').addClass('nav-show');
            sayHello.removeClass('nav-hide').addClass('nav-show');
        }

    })
}
menuClick();
$(window).scroll(function () {
    if ($(window).scrollTop() > 140) {
        $(".right-menu").addClass("right-menu-2");
    }
    else {
        $(".right-menu").removeClass("right-menu-2");
    }
});
function sayHello() {
    var sayHello = $('.say_hello');
    var helloBox = $('.hello_box')
    sayHello.on('click', function () {
        helloBox.show();
        typed.start();
    })
    helloBox.on('click', '.close_sayhello', function () {
        helloBox.hide();
        typed.stop();
    });
}
//點擊右側導航彈框
sayHello();
//Footer文字動畫
var typed = new Typed('.js-typed', {
    strings: [
        "喜歡設計",
        "不討厭汪汪喵喵",
        "很環保",
        "想要跟我們一起得獎",
        "喜歡飛機稿",
        "煩惱該怎麼創業",
        "超多點子無法執行",
        "天天上班很無聊",
        "下班喜歡小酌",
        "超愛上海",
        "覺得自己很時髦"
    ],
    typeSpeed: 50,
    backSpeed: 50,
    backDelay: 500,
    loop: true,
    showCursor: false,
    onComplete: function () {
    },
    onStop: function () {
    },
    onStart: function () {
    },
    onReset: function () {
    }, //還沒搞定
});
   typed.stop();

function navClick(){
    var navUl=$('.js_nav');
    navUl.find('.menu_open_icon').on('click',function () {
        if($(this).hasClass('active')){
            $(this).removeClass('active');
            $(this).parents('li').find('ul').hide();
        }else{
            $(this).addClass('active');
            $(this).parents('li').find('ul').show();
        }

    })
//        }
}
//click右側導航
navClick();