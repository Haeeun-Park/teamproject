<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<script type="text/javascript">
    opener.document.insertBoard.savefilename.value='${savefilename}';
    opener.document.insertBoard.image.value='${image}';
    opener.document.getElementById("image").innerHTML='${image}';
    opener.document.getElementById("savefilename").innerHTML='${savefilename}';

    opener.document.getElementById("previewimg").setAttribute('src','/upload/' + '${savefilename}');
    opener.document.getElementById("previewimg").style.display='inline'; // 이미지 미리보기

    self.close();
</script>
</body>
</html>
