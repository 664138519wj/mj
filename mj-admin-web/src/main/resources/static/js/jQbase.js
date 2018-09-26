// JavaScript Document
//随移固定 1.作用id 2.起始高度 3.结束高度 4.上边距or下边距 5.垂直边距
function scr_fixed(id,startH,finishH,vertical_type,mar){
	var obj=document.getElementById(id);
	if (document.all){
		var browser=navigator.appName 
		var b_version=navigator.appVersion 
		var version=b_version.split(";"); 
		var trim_Version=version[1].replace(/[ ]/g,""); 
		if(browser=="Microsoft Internet Explorer" && trim_Version=="MSIE6.0") {var llq="ie6"}else{var llq="ie"}
	}else{var llq="noie"}
	var inNun=false;
	var inNun2=false;
	var inNun3=false;
	$(window).scroll(function(){ 
		var allH=document.body.scrollHeight-finishH;
		var scroH=document.documentElement.scrollTop+document.body.scrollTop;
		//右侧移动
		if(scroH >startH && scroH <allH){
			if (llq=="ie6") {
				if(vertical_type=="t"){
					obj.style.cssText="margin-top:"+mar+"px;position:absolute;top:"+scroH+"px";
				}else if(vertical_type=="b"){
					obj.style.cssText="margin-bottom:"+mar+"px;position:absolute;top:"+scroH+"px";
				}
				}else{
					if (!inNun){
						if(vertical_type=="t"){
							obj.style.cssText="margin-top:"+mar+"px;top:0px;position:fixed;";
						}else if(vertical_type=="b"){
							obj.style.cssText="margin-bottom:"+mar+"px;top:auto;bottom:0px;position:fixed;";
						}
						inNun=true; 
						}else return false;
					}
					inNun2=false; 
					inNun3=false; 
					}else if(scroH >allH){
						if (!inNun3){
							if(vertical_type=="t"){
								obj.style.cssText="margin-top:0px;position:absolute;top:"+allH+"px";
							}else if(vertical_type=="b"){
								obj.style.cssText="margin-bottom:0px;position:absolute;top:auto;bottom:"+allH+"px";
							}
							inNun3=true; 
							}else return false;
							inNun=false;
					}else{
						inNun=false;
						if (llq=="ie6") {
							if(vertical_type=="t"){
								obj.style.cssText="margin-top:0px;position:absolute;";
							}else if(vertical_type=="b"){
								obj.style.cssText="margin-bottom:0px;position:absolute;";
							}
							}else{
								if (!inNun2){
									if(vertical_type=="t"){
										obj.style.cssText="margin-top:0px;position:absolute;";
									}else if(vertical_type=="b"){
										obj.style.cssText="margin-bottom:0px;position:absolute;";
									}
									inNun2=true; 
							}else return false;
						}
					}
		})
}

//导游层
function guide_frm(){ 
	//var h = $(window).height()-130;
	//$('.guide_frm').height(h);
	//var startH = $('#guide_frm').offset().top - 130;
	//随移固定 1.作用id 2.起始高度 3.结束高度 4.上边距or下边距 5.垂直边距
	scr_fixed('guide_frm',140,0,'t',140);
	$(window).scroll(function(){
		var h = $(window).height();
		$('.guide_frm').height(h);
	})
}

//详细页导游层
function particular_guide(){
	//var h = $(window).height();
	//$('.statu_slider_m').height(h);
	//随移固定 1.作用id 2.起始高度 3.结束高度 4.上边距or下边距 5.垂直边距
	scr_fixed('statu_slider',140,200,'t',60);
}

//关于页面 轴判断
function about_zhou(){
	var length = $('#guide_frm li').length;
	var steph = new Array();
	for(var i = 1; i<=length; i++){
		steph[i]=$('#about_part'+i).offset().top - 140;
	}
	steph[1] -= 5;
	steph[length+1] = 9999;
	
	$('#guide_frm li').last().css('padding-bottom','10px')
	var cur_heith=$(window).height()-140;
	var lable_height = 28;//右侧标签li高度
	var prev_height;
	function ensure_part(){
		var chushi_hei=0, hei1=0,hei11=0,y_inside_heiht=0;
		var hei = document.body.scrollTop;
		if ($.browser.msie || $.browser.mozilla){
			var hei = document.documentElement.scrollTop;
		}
		var now = $('#guide_frm li').eq($('#guide_frm .cur').parents('li').index());
		var next = now.next();
		var prev = now.prev();
		var h = steph[next.index() + 1];
		var guide_offt = parseInt($('#guide_frm').css('top')) - hei;
	//	if(guide_offt < 0 ) guide_offt = 10;
		guide_offt = 10;
		var num = length;
		var guide_height = 0;
		for(var i = num; i >= 0; i--){
			guide_height += $('.guide_frm_link li').eq(i).outerHeight();
			if(i == length - 1) guide_height -= 10;
		}
		var win_h = $(window).height() - guide_offt - guide_height - 140;
		now.siblings('li').css('margin-bottom',0);
			var y_lable_heith=parseInt(now.css('margin-bottom'));//y轴标签之间的距离
		
			// var margin1=parseInt(y_lable_heith-Math.round((hei-hei1)/(steph[jj+1]-steph[jj])*win_h));
		
		
			var y_lable_heith=parseInt(now.css('margin-bottom'));//y轴标签之间的距离		
			var left_current_height=h-steph[next.index()];//左边栏目内容之间的高度 不是动态的

			
			var lable_count_height = (num-next.index())*lable_height;//右侧还没有向上滚动的标签的总高度
		
			var left_current_d_height=hei+cur_heith-steph[next.index()]-20;//左边随着滚动条 滚动 两个标签中间距离的变化值
			var left_prev_d_height=steph[next.index()]-steph[next.index()-1];//左边随着滚动条 滚动 两个标签中间距离的变化值
			
			left_current_d_height = left_current_d_height-lable_count_height;
			
			var right_rate_d_height = (win_h/left_current_height)*left_current_d_height;//等比例转换后的实时高度
			
			//document.getElementById("name1").innerHTML=win_h+"=="+steph[next.index()]+"=="+left_prev_d_height;
			if(next.index()==1){
				now.css('margin-top',0);
			    now.css('margin-bottom',win_h); 
			}else{
			     if(left_prev_d_height<=cur_heith){
						// if(prev.css('margin-top')!='0px'){//判断如果向下滚动时 上一个标签如果不为0xp时置为0 防止向下拖动的时候 标签闪动
							
							if(!prev_height) prev_height=prev.css('margin-top').replace('px','');
						   // var prev_height=prev.css('margin-top').replace('px','');
						   // alert(prev_height);
						   var pre_margin = win_h-left_prev_d_height-left_current_d_height;
						   if (pre_margin<=0){
							   pre_margin=0;
							   } 
						   //document.getElementById("name2").innerHTML=win_h+"=="+left_current_d_height+"=="+prev_height;
						   prev.css('margin-top',pre_margin);
						    if (left_current_d_height>=win_h){
								now.css('margin-top',0);
								now.css('margin-bottom',left_current_d_height);	
							}else{
								now.css('margin-top',win_h-pre_margin-left_current_d_height);
								now.css('margin-bottom',left_current_d_height);	
							}

						// }else{
							
							
						// }
			      }else{
					if (left_current_d_height>=win_h) left_current_d_height=win_h;//如果高度大于win_h 则让滚动条滚动距离为win_h 也就是margin-top:0px;
					
					if(next.css('margin-top')!='0px'){//判断如果向上滚动时 下一个标签如果不为0xp时置为0 防止向上拖动的时候 将标签向下拉
						next.css('margin-top',0);
					}
					now.css('margin-top',win_h-left_current_d_height);
					now.css('margin-bottom',left_current_d_height);			
					
				  }
				  
					if(next.index()!=1){                       //bug判定
						$('#guide_frm li:first').css('margin-top',0);
					}
		        
			}
	}
	ensure_part();
	
	$(window).scroll(function(){
		var hei = document.body.scrollTop;
		if ($.browser.msie || $.browser.mozilla){
			  var hei = document.documentElement.scrollTop;
		  }
		for(var i = 1; i<=length; i++){
			// prev_height=0;
			if(hei<=(steph[i+1]-cur_heith+((length-i)*28))){
				$('#guide_frm li').eq(i-1).find('a').addClass('cur').parents('li').siblings('li').find('a').removeClass('cur');
				break;
			}
		}
		ensure_part();
	})
	
	$('#guide_frm li a').click(function(){
	   var eq = $(this).parents('li').index() + 1;
	   var h = parseInt(steph[eq] + 1);
	   $('html,body').animate({'scrollTop':h},300);
	})
	
	//var txt = '<div class="footer_blank"><div class="footer_blank_m"></div></div>';
	//$('body').append(txt);
}

//年份 轴
function year_zhou(){
	var length = $('#guide_frm li').length;
	var steph = new Array();
	for(var i = 1; i<=length; i++){
		steph[i]=$('#year_num'+i).offset().top - 150;
	}
	steph[length+1] = 99999;
	
	$('#guide_frm li').last().css('padding-bottom','10px')
	var cur_heith=$(window).height()-140;
	var lable_height = 24;//右侧标签li高度
	
	function ensure_part(){
		var hei = document.body.scrollTop;
		if ($.browser.msie || $.browser.mozilla){
			var hei = document.documentElement.scrollTop;
		}
		var now = $('#guide_frm li').eq($('#guide_frm .cur').parents('li').index());
		var next = now.next();
		var prev = now.prev();
		var h = steph[next.index()+1];
		var guide_offt = parseInt($('#guide_frm').css('top')) - hei;
		if(guide_offt < 0 ) guide_offt = 5;
		//var num = now.index() + 2;
		var num = length;
		var guide_height = 0;
		//for(var i = num - 1; i >= 0; i--){
		for(var i = num; i >= 0; i--){
			guide_height += $('.guide_frm_link li').eq(i).outerHeight();
			if(i==length - 1) guide_height = guide_height - 6;
		}
		if(hei == 0){
			var win_h = $(window).height() - guide_offt - guide_height - 5;
		}else{
			var win_h = $(window).height() - guide_offt - guide_height - 135;
		}
		now.siblings('li').css('margin-bottom',0);
		
		    var y_lable_heith=parseInt(now.css('margin-bottom'));//y轴标签之间的距离
		
			// var margin1=parseInt(y_lable_heith-Math.round((hei-hei1)/(steph[jj+1]-steph[jj])*win_h));
		
		
			var y_lable_heith=parseInt(now.css('margin-bottom'));//y轴标签之间的距离		
			var left_current_height=h-steph[next.index()];//左边栏目内容之间的高度 不是动态的

			
			var lable_count_height = (num-next.index())*lable_height;//右侧还没有向上滚动的标签的总高度
		
			var left_current_d_height=hei+cur_heith-steph[next.index()]-20;//左边随着滚动条 滚动 两个标签中间距离的变化值
			var left_prev_d_height=steph[next.index()]-steph[next.index()-1];//左边随着滚动条 滚动 两个标签中间距离的变化值
			
			left_current_d_height = left_current_d_height-lable_count_height;
			
			var right_rate_d_height = (win_h/left_current_height)*left_current_d_height;//等比例转换后的实时高度
			
			
			if(next.index()==1){
				now.css('margin-top',0);
			    now.css('margin-bottom',win_h); 
			}else{
				var cur_left_num;
				if(!next.index()){
					cur_left_num = 1;
				}else{
					cur_left_num = next.index();
				}
					var each_margin_top;
					for(var j=1;j<=cur_left_num;j++){
					var each_margin_obj = $('#guide_frm li').eq(j) ;
					each_margin_top = each_margin_obj.css('margin-top').replace('px','');
					if(each_margin_top!=0){						
							if(each_margin_top-left_current_d_height<=0){
								each_margin_obj.css('margin-top',0);
							}else{
								each_margin_obj.css('margin-top',each_margin_top-left_current_d_height);
							}
						}
					}
				
					if (left_current_d_height>=win_h) left_current_d_height=win_h;//如果高度大于win_h 则让滚动条滚动距离为win_h 也就是margin-top:0px;
					now.css('margin-top',win_h-left_current_d_height);
					now.css('margin-bottom',left_current_d_height);			
					
					if(next.css('margin-top')!='0px'){//判断如果向上滚动时 下一个标签如果不为0xp时置为0 防止向上拖动的时候 将标签向下拉
						next.css('margin-top',0);
					}
					if(prev.css('margin-top')!='0px'){//判断如果向下滚动时 上一个标签如果不为0xp时置为0 防止向下拖动的时候 标签闪动
						// prev.css('margin-top',0);
					}
				  
					if(next.index()!=1){                       //bug判定
						$('#guide_frm li:first').css('margin-top',0);
					}
		        
			}
		
		
		
	}
	ensure_part();
	
	$(window).scroll(function(){
		var hei = document.body.scrollTop;
		if ($.browser.msie || $.browser.mozilla){
			var hei = document.documentElement.scrollTop;
		}
		for(var i = 1; i<=length; i++){
			
				if(hei<=(steph[i+1]-cur_heith+((length-i)*28))){
					/*if(steph[2]-steph[1]<cur_heith){
				       $('#guide_frm li').eq(1).find('a').addClass('cur').parents('li').siblings('li').find('a').removeClass('cur');
					   break;
			           }else{*/
						  
					$('#guide_frm li').eq(i-1).find('a').addClass('cur').parents('li').siblings('li').find('a').removeClass('cur');
					break;
					  /* }*/
				}
			
		}
		ensure_part();
	})
	
	$('#guide_frm li a').click(function(){
	   var eq = $(this).parents('li').index() + 1;
	   var h = parseInt(steph[eq] + 1);
	   $('html,body').animate({'scrollTop':h},300);
	})
	
	//var txt = '<div class="footer_blank"><div class="footer_blank_m"></div></div>';
	//$('body').append(txt);
	//var fh = $('.footer_blank').height();
	//$('body').css('padding-bottom',fh+'px');
}
  
//返回上一页
function pageBack(){
	history.back();
}  
$(window).scroll(function(){
	var s = $(window).scrollTop();
	if (s > 0 && s<140) {
		$(".guide_frm_link li").eq(1).css("margin-top",0);
	}
});
//返回顶部 1.一个数字，用来控制返回顶部靠近左边主体的距离 2.是否显示返回顶部 3.是否显示返回前页
function goTop(num,isTop,isBack){

 
	var txt = '';
	if(isTop){
		txt += '<a href="javascript:" id="goTop_a" title="返回顶部"></a>';
	}
	if(isBack){
		txt += '<a href="javascript:" id="goBack_a" title="返回上页"></a>';
	}
	//txt += '</div>';
	$('#goTop').append(txt);
	var selfW = $('#goTop').width();
	var defR = $('#goTop').css('right');	//默认right
	$('#goTop_a').hide();
	/*
	function setR(num){
		var rightP = 0;
		var setS = 50;							//设置与主div距离
		var mainW = num;						//网页主宽度
		var cilW = $(window).width();
		rightP = (cilW - mainW) / 2 - selfW - setS;
		var bi = mainW + selfW + setS + 140;
		if(cilW <= bi){
			$('#goTop').animate({'right':defR},0);
		}else{
			$('#goTop').animate({'right':rightP},0);
		}
	}
	
	setR(num);
	$(window).resize(function(){
		setR(num);
	})
	*/
	
	function isTop(){
		var scrTop = $(document).scrollTop();
		if(scrTop != 0){$('#goTop_a').fadeIn(300);}
		else{$('#goTop_a').fadeOut(300);}
	}
	$(window).scroll(function(){ isTop(); })
	$('#goTop_a').click(function(){$('html,body').animate({scrollTop:'0px'}, 500);})
	$('#goBack_a').click(function(){
		pageBack();
	})
}