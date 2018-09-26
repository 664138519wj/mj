$(function(){
//        个人练习记录
//    $('.check-error').click(function(){
//        $(this).parents('.tab').hide();
//        $('.check-box').show();
//        $('.review-box').hide();
//    });
//    $('.review').click(function(){
//        $(this).parents('.tab').hide();
//        $('.review-box').show();
//        $('.check-box').hide();
//    });
//       选择题
    $('.subject-detail ul li').click(function(){
        $(this).addClass("active").siblings().removeClass("active");
        $(this).find("input[type=radio]").prop("checked",true);
    });
//        购买练习卡
    $('.per-menu ul li.buy-card').click(function(){
        $('.buy-card-box').show();
    });
    $('.cancel').click(function(){
        $('.buy-card-box').hide();
    });
    $('.sure').click(function(){
        $('.buy-card-box').hide();
    });

//        自主预约考试
    $('.car-main ul li').click(function(){
        $(this).addClass('car-active').siblings('li').removeClass('car-active');
    });
//        自主预约考试——选择车型
    $('.car-main ul .type-1').click(function(){
        $('.car-types1').show();
    });
    $('.pay-box ul li input').click(function(){
        if($(this).prop("checked",true)){
            $('#type1').val($(this).next('span').text());
            console.log("type1:" + $("#type1").val());
        }
    });
    $('.car-main ul .type-2').click(function(){
        $('.car-types2').show();
    });
    $('.car-main ul .type-3').click(function(){
        $('.car-types3').show();
    });
    $('.car-main ul .type-4').click(function(){
        $('.car-types4').show();
    });

    //        自主预约考试-----选择驾校
    $('.test-subject>.school-1').click(function(){
        $(".prices").show();
    });
    $('.test-subject>.school-2').click(function(){
        $(".names").show();
    });
    $('.prices li input[type="radio"]').click(function(){
        if($(this).prop("checked",true)){
            $('#price').text($(this).next('span').next('span').text());
            $('#prices').val($('#school').text($(this).next('span').text()));
            $("#schoolId").val($(this).val());
            $("#schoolName").val($(this).next('span').text());
            console.log("schoolId:" + $("#schoolId").val());
            console.log("schoolName:" + $("#schoolName").val());

        }
        $('.school-name').hide();
    });
    //        自主预约考试-----选择考场
    $('.names li input').click(function(){
        if($(this).prop("checked",true)){
            $('#schools').val($(this).next('span').text());
            $('#examPlaceId').val($(this).val());
            $('#examPlaceName').val($(this).next('span').text());
            console.log("examPlaceId:" + $("#examPlaceId").val());
            console.log("examPlaceName:" + $("#examPlaceName").val());
        }
        $('.school-name').hide();
    });
    //        自主预约考试-----考试科目切换
    $('.test-subject ul li').click(function(){
        $(this).addClass('active').siblings('li').removeClass('active');
    });

//        驾校报名-----选择地区、价格、车型
    $(".choose-ul>li .area-box").click(function(){
        $(this).next(".child-box").show();
        $(this).parent('li').siblings().find('.child-box').hide();
    });
    $(".choose-ul>li .child-box li").not(".xc-subject").click(function(){
        $(this).parents('.child-box').prev('.area-box').find('.word').val($(this).text());
        $(this).parents('.child-box').prev('.area-box').find('.key').val($(this).find('input').val());
        $(this).parents('.child-box').hide();
        console.log($(this).find('input').val());
    });
//        驾校报名-----点击驾校进入报名详情
//    $('.tab .school-box .info-box li').click(function(){
//        $('.school-box').hide();
//        $('.sign-box').show();
//    });
//        驾校报名-----点击我要报名进入填写信息
//    $('.course li button').click(function(){
//        $('.course').hide();
//        $('.fill-info').show();
//    });
//        驾校报名-----点击按钮选择
    $(".fill-info ul li .fill-name button").click(function(){
        $(this).addClass('choose-btn');
        $(this).prev('button').removeClass('choose-btn');
        $(this).next('button').removeClass('choose-btn');
    });
    
    
    
    
    

	// 预约报名提交 ---------start---------------
    // 预约考场查询
    $("#query_appoint_button").click(function() {
		$('#kemu').val($('#kemu_class').val());
		$('#kemuStr').val($('#kemu_str').val());
		$('#licence').val($('#licence_class').val());
		$('#licenceName').val($('#licence_name').val());
        if (kemu == '' || kemu == undefined) {
        	alert("请选择科目");
        	return;
        }
        if (licence == '' || licence == undefined) {
        	alert("请选择驾照类型");
        	return;
        }
		var form = $("#appoint_school_form");
		form.submit();
	});
	function checkForm(arr) {
		for (var i = 0; i < arr.length; i++) {
			if ($("#" + arr[i][0]).val() == '') {
				alert(arr[i][1]);
				$("#" + arr[i][0]).focus();
				return false;
			}
		}
		return true;
	};
	// 调用方式
	var arr = new Array(
			new Array('licenceType', '请选择驾照类型!'),
			new Array('kemu','请选择科目!'), 
			new Array('startTime', '请选择考试时间!'),
			new Array('realName', '请输入用户名!'),
			new Array('idNo', '请输入身份证号!'),
			new Array('examPlaceId', '请选择考场!')
    );
	$("#submit_appointment_button").click(function(){
		// 获取科目
//		$('#kemu_class').find('li').each(function() {
//            if ($(this).hasClass('active')) {
//            	$('#kemu').val($(this).val());
//            }
//        })
        // 获取支付类型
        $('#payType_class').find('li').each(function() {
            if ($(this).hasClass('active')) {
            	$('#payType').val($(this).val());
            }
        })
//        $('#startTime').val($('#dt').val());
        
        var idNo = $("#idNo").val();
        if(idNo.search(/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/) == -1) {
            alert('请输入正确格式的身份证号码！');
            return false; 
        }
		if (!checkForm(arr)) {
			return false;
		}
		var dataParam = $("#appointment_form").serialize();
		console.log(dataParam);
		jQuery.ajax({
			type : "post",
			url : $("#appointment_form").attr("action"),
			data : dataParam,
			success : function(data) {
				if (data) {
					// 线上付款 跳转付款选择页面
					var result = data.data;
					if(result.payType == 0) {
						window.location.href=$('#relocationUrl2').val() + "?courseId=" + result.id + "&courseType=" + 5 + "&price=" + result.price;
					} else {
						window.location.href=$('#relocationUrl').val();
					}
				} else {
					alert("error");
				}
			},
			error : function(e) {
			}
		})
	});
	// 预约报名提交 ---------end---------------
	
	// 驾校 ---------start---------------
	$("#query_school_button").click(function() {
		$('#areaId').val($('#area_class').val());
		var range = $('#pricerange_class').val();
		$('#priceFrom').val(range.split("-")[0]);
		$('#priceTo').val(range.split("-")[1]);
		$('#licence').val($('#licence_class').val());
		$('#areaName').val($('#area_name').val());
		$('#priceStr').val($('#price_str').val());
		$('#licenceName').val($('#licence_name').val());


		console.log("areaId:" + $('#areaId').val());
		console.log("priceFrom:" + $('#priceFrom').val());
		console.log("priceTo:" + $('#priceTo').val());
		console.log("licence:" + $('#licence').val());
		var form = $("#driving_school_form");
		form.submit();
	});
	
	var arr2 = new Array(
			new Array('userName', '姓名不能为空!'),
			new Array('telNo','手机号码不能为空!'), 
			new Array('idNo','身份证号码不能为空!'), 
			new Array('idUp', '请上传身份证正面!'),
			new Array('idDown', '请上传身份证反面!')
    );
	$("#driving_school_submit").click(function(){		
		$('#sex_div').find('button').each(function() {
            if ($(this).hasClass('choose-btn')) {
            	$('#sex').val($(this).val());
            }
        })
        $('#firstApply_div').find('button').each(function() {
            if ($(this).hasClass('choose-btn')) {
            	$('#firstApply').val($(this).val());
            }
        })
        $('#payType_div').find('button').each(function() {
            if ($(this).hasClass('choose-btn')) {
            	$('#payType').val($(this).val());
            }
        })
        
        //自主预约and驾校报名验证手机号码and身份证号码
        var telNo = $("#telNo").val();
        if(telNo.search(/^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1})|(16[0-9]{1})|(19[0-9]{1}))+\d{8})$/) == -1) {
            alert('请输入大陆手机号码！');
            return false; 
        }
        
        var idNo = $("#idNo").val();
        if(idNo.search(/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/) == -1) {
            alert('请输入正确格式的身份证号码！');
            return false; 
        }
       
        
        var idUpStr = $('#facade')[0].src;
		if (idUpStr != undefined && idUpStr.indexOf(",") != -1) {
	        $('#idUp').val(idUpStr.split(",")[1]);
		}
		var idDownStr = $('#identity')[0].src;
		if (idDownStr != undefined && idDownStr.indexOf(",") != -1) {
	        $('#idDown').val(idDownStr.split(",")[1]);
		}
		if (!checkForm(arr2)) {
			return false;
		}
		var dataParam = $("#driving_school_form").serialize();
		jQuery.ajax({
			type : "post",
			url : $("#driving_school_form").attr("action"),
			data : dataParam,
			success : function(data) {
				if (data) {
					if (data.status == '0') {
						alert(data.msg);
						return;
					} 
					var result = data.data;					
					if(result.payType == 0) {
						window.location.href=$('#relocationUrl2').val() + "?courseId=" + result.courseId + "&courseType=" + 3 + "&price=" + result.price;
					} else {
						window.location.href=$('#driving_relocation_url').val();
					}
				} else {
					alert("error");
				}
			},
			error : function(e) {
			}
		})
	});
	// 驾校 ---------end---------------


});
// 上传图片
$("#facade_card").change(function() {
    if(checkPic()) {
        $(".tximg").attr("src", preImg(this.id, 'facade'));
        $('.img1').hide();
    }
});

function checkPic() {
    var picPath = $("#facade_card").val();
    var type = picPath.substring(picPath.lastIndexOf(".") + 1, picPath.length).toLowerCase();
    if(type != "jpg" && type != "bmp" && type != "gif" && type != "png") {
        alert("请上传正确的图片格式");
        return false;
    }
    return true;
}
$("#identity_card").change(function() {
    if(checkPic1()) {
        $(".tximg").attr("src", preImg(this.id, 'identity'));
        $('.img2').hide();
    }
});

function checkPic1() {
    var picPath = $("#identity_card").val();
    var type = picPath.substring(picPath.lastIndexOf(".") + 1, picPath.length).toLowerCase();
    if(type != "jpg" && type != "bmp" && type != "gif" && type != "png") {
        alert("请上传正确的图片格式");
        return false;
    }
    return true;
}

function preImg(sourceId, targetId) {
    if(typeof FileReader === 'undefined') {
        alert('Your browser does not support FileReader...');
        return;
    }
    var reader = new FileReader();
    reader.onload = function(e) {
        var img = document.getElementById(targetId);
        img.src = this.result;
        return img.src;
    };
    reader.readAsDataURL(document.getElementById(sourceId).files[0]);
}
//    已报名驾校-----申请退款
$('.refund').click(function(){
    $('#refund').show();
})

