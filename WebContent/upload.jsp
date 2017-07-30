<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<style>
#byte_content {
	margin: 5px 0;
	max-height: 100px;
	overflow-y: auto;
	overflow-x: hidden;
}

#byte_range {
	margin-top: 5px;
}
</style>
	<span class="file-input btn btn-primary btn-file"> <input
		type="file" id="file1" name="file" /> Read bytes:
	</span>
	<span class="readBytesButtons">
		<button class="btn btn-primary" data-startbyte="0" data-endbyte="4">1-5</button>
		<button class="btn btn-primary" data-startbyte="5" data-endbyte="14">6-15</button>
		<button class="btn btn-primary" data-startbyte="6" data-endbyte="7">7-8</button>
		<button class="btn btn-primary">entire file</button>
	</span>
	<div id="byte_range"></div>
	<div id="byte_content"></div>

	<script>
		function readBlob(opt_startByte, opt_stopByte) {

			var files = document.getElementById('file1').files;
			if (!files.length) {
				alert('Please select a file!');
				return;
			}

			var file = files[0];
			var start = parseInt(opt_startByte) || 0;
			var stop = parseInt(opt_stopByte) || file.size - 1;

			var reader = new FileReader();

			// If we use onloadend, we need to check the readyState.
			reader.onloadend = function(evt) {
				if (evt.target.readyState == FileReader.DONE) { // DONE == 2
					document.getElementById('byte_content').textContent = evt.target.result;
					document.getElementById('byte_range').textContent = [
							'Read bytes: ', start + 1, ' - ', stop + 1, ' of ',
							file.size, ' byte file' ].join('');
				}
			};

			var blob = file.slice(start, stop + 1);
			reader.readAsBinaryString(blob);
		}

		document.querySelector('.readBytesButtons').addEventListener(
				'click',
				function(evt) {
					if (evt.target.tagName.toLowerCase() == 'button') {
						var startByte = evt.target
								.getAttribute('data-startbyte');
						var endByte = evt.target.getAttribute('data-endbyte');
						readBlob(startByte, endByte);
					}
				}, false);
	</script>
</body>
</html>