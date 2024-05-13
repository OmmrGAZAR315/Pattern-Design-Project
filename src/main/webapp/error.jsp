<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error</title>
</head>
<body>
<h1>Error</h1>
<p><%= request.getAttribute("message") == null ? "" : request.getAttribute("message") %></p>
<p><%= request.getAttribute("status_code") == null ? "" : request.getAttribute("status_code") %></p>
<p>Please try again later or contact support for assistance.</p>
</body>
</html>
