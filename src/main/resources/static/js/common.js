let getData = () => {
	$('#data-table').removeClass('d-none');
	$('#get-data-btn').addClass('d-none');
	$('#go-back-btn').removeClass('d-none');
	$.ajax({
		url: `/parser`,
		type: 'GET',
		dataType: "json",
		success: (data) => {
			const table = $('#body-table');
			$.each(data, function(key, value) {

				$('<tr><td>' + value.time + '</td><td><p>#1 ' + value.teamName1 + '</p><p>#2 ' +
					value.teamName2 + '</p></td><td>' + value.tournament + '</td><td>' +
					value.sport + '</td><td><a href="' + value.link + '">Details</a></td></tr>')
					.appendTo(table);

			});
		}
	});
};

$('#time').click(function() {
	var table = $(this).parents('table').eq(0);
	var rows = table.find('tr:gt(0)').toArray().sort(comparer($(this).index()));
	this.asc = !this.asc;
	if (!this.asc) {
		rows = rows.reverse();
	}
	for (var i = 0; i < rows.length; i++) {
		table.append(rows[i]);
	}
})
function comparer(index) {
	return function(a, b) {
		var valA = getCellValue(a, index), valB = getCellValue(b, index);
		return $.isNumeric(valA) && $.isNumeric(valB) ?
			valA - valB : valA.toString().localeCompare(valB);
	}
}
function getCellValue(row, index) {
	return $(row).children('td').eq(index).text();
}