<html>
<body>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>

<div>
    <form action="/view/insert.do" method="post" enctype="multipart/form-data">
        <tr>
            <td>内容</td>
            <td><input type="text" name="content"/></td>
        </tr>
        <tr>
            <td>文件</td>
            <td><input type="file" name="file"></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="发表"></td>
        </tr>

    </form>

</div>
</body>
</html>
