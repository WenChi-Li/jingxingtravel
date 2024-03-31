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
	<div class="d-flex flex-row">
		<jsp:include page="/components/adminSidebar.jsp" />
		<div class="container">
			<div class='alert alert-success'>
				<h2 align='center'>討論區管理系統</h2>
			</div>


			<div class="container">

				<form action="/articles" method="post" id="formdata">
					<input hidden type="text" name="articleId"
						value="${Article.articleId}" id="articleId"> <label>子版：</label>
					<select class="form-control" id="cselect" name="categoryId">
						<c:forEach var="it" items="${forum.categories }">
							<c:choose>
								<c:when test="${it.categoryId==Article.categories.categoryId }">
									<option value="${it.categoryId }" selected>${it.categoryName }</option>
								</c:when>
								<c:otherwise>
									<option value="${it.categoryId }">${it.categoryName }</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select> <label>類型：</label><select class="form-control" id="tselect"
						name="articleTypeId">
						<c:forEach var="it" items="${articleTypes }">
							<c:choose>
								<c:when
									test="${it.articleTypeId==Article.articleTypes.articleTypeId }">
									<option value="${it.articleTypeId }" selected>${it.articleTypeName }</option>
								</c:when>
								<c:otherwise>
									<option value="${it.articleTypeId }">${it.articleTypeName }</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select> <label>標題：</label> <input type="text" class="form-control"
						name="articleTitle" id="atitle" value="${Article.articleTitle }" />
					<label>文章內容：</label>
					<textarea id="editor" name="articleContent"></textarea>
					<button type="button" class="btn btn-primary" id="updatebtn">更新</button>
					<a href="/back/article?articleId=${Article.articleId }"
						class="btn btn-secondary" id="cancelbtn">取消</a>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">

$(function () {
	
var articleContent = '${Article.articleContent }';


editor.setData(articleContent);

$("#updatebtn").click(function () {
	
	let date = moment().format();
	
	
	let userdata = sessionStorage.getItem("loginStatus");
	userdata=JSON.parse(userdata);
	
	let formdata = 
		{
			articleDate:date,
	        mId:userdata.mid,
	        categoryId:$("#cselect").val(),
		    articleTypeId:$("#tselect").val(),
		    articleTitle:$("#atitle").val(),
		    articleContent:editor.getData(),
		    articleId:$("#articleId").val(),
		}
		
	
	console.log(formdata);
	
	fetch("/articles",{
		method:"put",
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
                	
                    window.location="http://localhost:8080/article?articleId="+data.articleId;
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