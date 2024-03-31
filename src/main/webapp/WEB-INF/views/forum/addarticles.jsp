<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>撰寫文章</title>
<jsp:include page="/components/common_imports.jsp"></jsp:include>
<script src="https://cdn.jsdelivr.net/npm/moment@2.30.1/moment.min.js"></script>
</head>
<body>
	<jsp:include page="/components/header.jsp"></jsp:include>

	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container">
			<a class="navbar-brand" href="/forums">討論區</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="/forums/${forum.forumId }">${forum.forumName }</a></li>
					<li class="nav-item"><a class="nav-link"
						href="/articles?forumId=${forum.forumId }">文章列表</a>
					</li>

				</ul>
			</div>
		</div>
	</nav>
	<div class="container">

		<form action="/articles" method="post" id="formdata">
			<input hidden type="text" name="articleDate" id="adate"> <input
				hidden type="text" name="mId" id="mid">
			<label>子版：</label> <select class="form-control" id="cselect"
				name="categoryId">
				<c:forEach var="it" items="${forum.categories }">
					<option value="${it.categoryId }">${it.categoryName }</option>
				</c:forEach>
			</select> <label>類型：</label><select class="form-control" id="tselect"
				name="articleTypeId">
				<c:forEach var="it" items="${articleTypes }">
					<option value="${it.articleTypeId }">${it.articleTypeName }</option>
				</c:forEach>
			</select> <label>標題：</label> <input type="text" class="form-control"
				name="articleTitle" id="atitle" /> <label>文章內容：</label>
			<textarea id="editor" name="articleContent"></textarea>
			<button type="button" class="btn btn-primary" id="insertbtn">新增</button>
			<a
				href="/articles?forumId=${forum.forumId }">
				<button type="button" class="btn btn-secondary" id="insertbtn">取消</button>
			</a>
		</form>
	</div>

	<script type="text/javascript">

$(function () {
	//抓取sessionStorage的userdata
	let userdata = sessionStorage.getItem("loginStatus");
	userdata=JSON.parse(userdata);
	
let date = moment().format();
$("#adate").val(date);

$("#mid").val(userdata.mid);


$("#insertbtn").click(function () {
	
	let formdata = 
		{
			articleDate:$("#adate").val(),
	        mId:$("#mid").val(),
	        categoryId:$("#cselect").val(),
		    articleTypeId:$("#tselect").val(),
		    articleTitle:$("#atitle").val(),
		    articleContent:editor.getData(),
		}
		
	
	console.log(formdata);
	
	fetch("/articles",{
		method:"post",
		body:JSON.stringify(formdata),
		headers: { 'Content-Type': 'application/json' },
		})
    .then(rs=>{
        return rs.json();
    })
    .then(data=>{
        if (data.success) {
            Swal.fire({
                title:data.msg,
                icon:"success",
                allowOutsideClick: false,
            }).then(rs=>{
                if (rs.isConfirmed) {
                    window.location.href="http://localhost:8080/articles?forumId=${forum.forumId}";
                }
            }
            )
        } else {
            Swal.fire({
                title:data.msg,
                icon:"error",
                allowOutsideClick: false,
            })
        }
    })
})
	
})


</script>



	<style>
#container {
	width: 1000px;
	margin: 20px auto;
}

.ck-editor__editable[role="textbox"] {
	/* Editing area */
	min-height: 200px;
}

.ck-content .image {
	/* Block images */
	max-width: 80%;
	margin: 20px auto;
}
</style>

	<!--
            The "superbuild" of CKEditor&nbsp;5 served via CDN contains a large set of plugins and multiple editor types.
            See https://ckeditor.com/docs/ckeditor5/latest/installation/getting-started/quick-start.html#running-a-full-featured-editor-from-cdn
        -->
	<script
		src="https://cdn.ckeditor.com/ckeditor5/41.2.0/super-build/ckeditor.js"></script>
	<!--
            Uncomment to load the Spanish translation
            <script src="https://cdn.ckeditor.com/ckeditor5/41.2.0/super-build/translations/es.js"></script>
        -->
	<script>
            // This sample still does not showcase all CKEditor&nbsp;5 features (!)
            // Visit https://ckeditor.com/docs/ckeditor5/latest/features/index.html to browse all the features.
   			CKEDITOR.ClassicEditor.create(document.getElementById("editor"), {
                // https://ckeditor.com/docs/ckeditor5/latest/features/toolbar/toolbar.html#extended-toolbar-configuration-format
                toolbar: {
                    items: [
                        'heading', '|',
                        'bold', 'italic', 'strikethrough', 'underline', '|',
                        'bulletedList', 'numberedList', '|',
                        'outdent', 'indent', '|',
                        'undo', 'redo',
                        'fontSize', 'fontFamily', 'fontColor', 'fontBackgroundColor', 'highlight', '|',
                        'alignment', '|',
                         'uploadImage', 'blockQuote', '|',
                        'specialCharacters', 'horizontalLine'
                    ],
                    shouldNotGroupWhenFull: true
                },
                // Changing the language of the interface requires loading the language file using the <script> tag.
                // language: 'es',
                list: {
                    properties: {
                        styles: true,
                        startIndex: true,
                        reversed: true
                    }
                },
                // https://ckeditor.com/docs/ckeditor5/latest/features/headings.html#configuration
                heading: {
                    options: [
                        { model: 'paragraph', title: 'Paragraph', class: 'ck-heading_paragraph' },
                        { model: 'heading1', view: 'h1', title: 'Heading 1', class: 'ck-heading_heading1' },
                        { model: 'heading2', view: 'h2', title: 'Heading 2', class: 'ck-heading_heading2' },
                        { model: 'heading3', view: 'h3', title: 'Heading 3', class: 'ck-heading_heading3' },
                        { model: 'heading4', view: 'h4', title: 'Heading 4', class: 'ck-heading_heading4' },
                        { model: 'heading5', view: 'h5', title: 'Heading 5', class: 'ck-heading_heading5' },
                        { model: 'heading6', view: 'h6', title: 'Heading 6', class: 'ck-heading_heading6' }
                    ]
                },
                // https://ckeditor.com/docs/ckeditor5/latest/features/editor-placeholder.html#using-the-editor-configuration
                placeholder: 'Welcome to CKEditor 5!',
                
                // https://ckeditor.com/docs/ckeditor5/latest/features/font.html#configuring-the-font-size-feature
                fontSize: {
                    options: [ 10, 12, 14, 'default', 18, 20, 22 ],
                    supportAllValues: true
                },
                // Be careful with the setting below. It instructs CKEditor to accept ALL HTML markup.
                // https://ckeditor.com/docs/ckeditor5/latest/features/general-html-support.html#enabling-all-html-features
                htmlSupport: {
                    allow: [
                        {
                            name: /.*/,
                            attributes: true,
                            classes: true,
                            styles: true
                        }
                    ]
                },
                // https://ckeditor.com/docs/ckeditor5/latest/features/link.html#custom-link-attributes-decorators
                link: {
                    decorators: {
                        addTargetToExternalLinks: true,
                        defaultProtocol: 'https://',
                        toggleDownloadable: {
                            mode: 'manual',
                            label: 'Downloadable',
                            attributes: {
                                download: 'file'
                            }
                        }
                    }
                },
                // https://ckeditor.com/docs/ckeditor5/latest/features/mentions.html#configuration
                mention: {
                    feeds: [
                        {
                            marker: '@',
                            feed: [
                                '@apple', '@bears', '@brownie', '@cake', '@cake', '@candy', '@canes', '@chocolate', '@cookie', '@cotton', '@cream',
                                '@cupcake', '@danish', '@donut', '@dragée', '@fruitcake', '@gingerbread', '@gummi', '@ice', '@jelly-o',
                                '@liquorice', '@macaroon', '@marzipan', '@oat', '@pie', '@plum', '@pudding', '@sesame', '@snaps', '@soufflé',
                                '@sugar', '@sweet', '@topping', '@wafer'
                            ],
                            minimumCharacters: 1
                        }
                    ]
                },
                // The "superbuild" contains more premium features that require additional configuration, disable them below.
                // Do not turn them on unless you read the documentation and know how to configure them and setup the editor.
                removePlugins: [
                    // These two are commercial, but you can try them out without registering to a trial.
                    // 'ExportPdf',
                    // 'ExportWord',
                    'AIAssistant',
                    'CKBox',
                    'CKFinder',
                    'EasyImage',
                    // This sample uses the Base64UploadAdapter to handle image uploads as it requires no configuration.
                    // https://ckeditor.com/docs/ckeditor5/latest/features/images/image-upload/base64-upload-adapter.html
                    // Storing images as Base64 is usually a very bad idea.
                    // Replace it on production website with other solutions:
                    // https://ckeditor.com/docs/ckeditor5/latest/features/images/image-upload/image-upload.html
                    // 'Base64UploadAdapter',
                    'RealTimeCollaborativeComments',
                    'RealTimeCollaborativeTrackChanges',
                    'RealTimeCollaborativeRevisionHistory',
                    'PresenceList',
                    'Comments',
                    'TrackChanges',
                    'TrackChangesData',
                    'RevisionHistory',
                    'Pagination',
                    'WProofreader',
                    // Careful, with the Mathtype plugin CKEditor will not load when loading this sample
                    // from a local file system (file://) - load this site via HTTP server if you enable MathType.
                    'MathType',
                    // The following features are part of the Productivity Pack and require additional license.
                    'SlashCommand',
                    'Template',
                    'DocumentOutline',
                    'FormatPainter',
                    'TableOfContents',
                    'PasteFromOfficeEnhanced',
                    'CaseChange'
                ]
            }).then(editor=>{
            	window.editor=editor;
            });
        </script>
</body>
</html>