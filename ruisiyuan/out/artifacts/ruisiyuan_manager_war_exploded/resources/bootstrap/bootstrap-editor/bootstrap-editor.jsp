<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
<head>
<base href="<%=basePath%>">

<title>bootstrap editor</title>
<%@include file="/resources/bootstrap/bootstrap-editor/bootstrap-edit-head.html"%>
</head>
<style>
#editor {
	overflow: scroll;
	max-height: 300px
}
</style>
<body>
	<div class="container" style="width:100%; ">
		<div class="hero-unit">
			<div id="alerts"></div>
			<div class="btn-toolbar" data-role="editor-toolbar"
				data-target="#editor">
				<div class="btn-group">
					<a class="btn dropdown-toggle" data-toggle="dropdown" title="Font"><i
						class="icon-font"></i><b class="caret"></b></a>
					<ul class="dropdown-menu">
					</ul>
				</div>
				<div class="btn-group">
					<a class="btn dropdown-toggle" data-toggle="dropdown"
						title="字体大小"><i class="icon-text-height"></i>&nbsp;<b
						class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a data-edit="fontSize 8"><font size="8">8</font></a></li>
						<li><a data-edit="fontSize 7"><font size="7">7</font></a></li>
						<li><a data-edit="fontSize 6"><font size="6">6</font></a></li>
						<li><a data-edit="fontSize 5"><font size="5">5</font></a></li>
						<li><a data-edit="fontSize 4"><font size="4">4</font></a></li>
						<li><a data-edit="fontSize 3"><font size="3">3</font></a></li>
						<li><a data-edit="fontSize 2"><font size="2">2</font></a></li>
						<li><a data-edit="fontSize 1"><font size="1">1</font></a></li>
					</ul>
				</div>
				<div class="btn-group">
					<a class="btn" data-edit="bold" title="加粗 (Ctrl + B)"><i
						class="icon-bold"></i></a> <a class="btn" data-edit="italic"
						title="斜体 (Ctrl + I)"><i class="icon-italic"></i></a> <a
						class="btn" data-edit="strikethrough" title="删除线"><i
						class="icon-strikethrough"></i></a> <a class="btn"
						data-edit="underline" title="下划线 (Ctrl + U)"><i
						class="icon-underline"></i></a>
				</div>
				<div class="btn-group">
					<a class="btn" data-edit="insertunorderedlist" title="项目符号"><i
						class="icon-list-ul"></i></a> <a class="btn"
						data-edit="insertorderedlist" title="数字符号"><i
						class="icon-list-ol"></i></a> <a class="btn" data-edit="outdent"
						title="缩进减 (Shift + Tab)"><i class="icon-indent-left"></i></a>
					<a class="btn" data-edit="indent" title="缩进加 (Tab)"><i
						class="icon-indent-right"></i></a>
				</div>
				<div class="btn-group">
					<a class="btn" data-edit="justifyleft"
						title="居左显示 (Ctrl + L)"><i class="icon-align-left"></i></a>
					<a class="btn" data-edit="justifycenter"
						title="居中显示 (Ctrl + E)"><i class="icon-align-center"></i></a>
					<a class="btn" data-edit="justifyright"
						title="居右显示 (Ctrl/Cmd+R)"><i class="icon-align-right"></i></a>
					<a class="btn" data-edit="justifyfull" title="默认 (Ctrl + J)"><i
						class="icon-align-justify"></i></a>
				</div>
				<div class="btn-group">
					<a class="btn dropdown-toggle" data-toggle="dropdown"
						title="超链接"><i class="icon-link"></i></a>
					<div class="dropdown-menu input-append">
						<input class="span2" placeholder="URL" type="text"
							data-edit="createLink" />
						<button class="btn" type="button">Add</button>
					</div>
					<a class="btn" data-edit="unlink" title="删除超链接"><i
						class="icon-cut"></i></a>
				</div>
				<!-- <div class="btn-group">
					<a class="btn dropdown-toggle" data-toggle="dropdown"
						title="插入视频"><i class="icon-film"></i></a>
					<div class="dropdown-menu input-append">
						<input class="span2" placeholder="视频地址" type="text"
							data-edit="createVedio" />
						<button class="btn" type="button">Add</button>
					</div>
				</div> -->
				<!--  -->
				<div class="btn-group">
					<a class="btn" data-edit="redo" title="插入图片 (可拖放)" id="pictureBtn">
						<i class="icon-picture"></i>
						<input type="file" data-role="magic-overlay" data-target="#pictureBtn" 
						data-edit="insertImage" />
					</a>
					<a class="btn" data-edit="undo" title="撤销 (Ctrl + Z)">
						<i class="icon-undo"></i>
					</a>
					<a class="btn" data-edit="redo" title="恢复 (Ctrl/Cmd+Y)">
						<i class="icon-repeat"></i>
					</a>
				</div>
				<div class="btn-group">
					
				</div>
				<input type="text" data-edit="inserttext" id="voiceBtn"
					x-webkit-speech="">
			</div>
			<div id="editor">输入内容&hellip;</div>
			
					</div>
	</div>
		<script>	
			$(function() {
				function initToolbarBootstrapBindings() {
					var fonts = [ '宋体', '黑体', '微软雅黑', 'Serif', 'Sans', 'Arial', 'Arial Black',
							'Courier', 'Courier New', 'Comic Sans MS',
							'Helvetica', 'Impact', 'Lucida Grande',
							'Lucida Sans', 'Tahoma', 'Times',
							'Times New Roman', 'Verdana' ], fontTarget = $(
							'[title=Font]').siblings('.dropdown-menu');
					$
							.each(
									fonts,
									function(idx, fontName) {
										fontTarget
												.append($('<li><a data-edit="fontName ' + fontName +'" style="font-family:\''+ fontName +'\'">'
														+ fontName
														+ '</a></li>'));
									});
					$('a[title]').tooltip({
						container : 'body'
					});
					$('.dropdown-menu input').click(function() {
						return false;
					}).change(
							function() {
								$(this).parent('.dropdown-menu').siblings(
										'.dropdown-toggle').dropdown('toggle');
							}).keydown('esc', function() {
						this.value = '';
						$(this).change();
					});

					$('[data-role=magic-overlay]').each(
							function() {
								var overlay = $(this), target = $(overlay
										.data('target'));
								overlay.css('opacity', 0).css('position',
										'absolute').offset(target.offset())
										.width(target.outerWidth()).height(
												target.outerHeight());
							});
					if ("onwebkitspeechchange" in document
							.createElement("input")) {
						var editorOffset = $('#editor').offset();
						$('#voiceBtn').css('position', 'absolute').offset(
								{
									top : editorOffset.top,
									left : editorOffset.left
											+ $('#editor').innerWidth() - 35
								});
						alert(editorOffset);
					} else {
						$('#voiceBtn').hide();
					}
				}
				;
				function showErrorAlert(reason, detail) {
					var msg = '';
					if (reason === 'unsupported-file-type') {
						msg = "Unsupported format " + detail;
					} else {
						console.log("error uploading file", reason, detail);
					}
					$(
							'<div class="alert"> <button type="button" class="close" data-dismiss="alert">&times;</button>'
									+ '<strong>File upload error</strong> '
									+ msg + ' </div>').prependTo('#alerts');
				}
				;
				initToolbarBootstrapBindings();
				$('#editor').wysiwyg({
					fileUploadError : showErrorAlert
				});
			});
		</script>
</body>
</html>
