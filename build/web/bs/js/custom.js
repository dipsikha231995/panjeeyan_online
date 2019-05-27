function PrintElem(elem, page_title)
{
    Popup($(elem).html(), page_title);
}

function Popup(data, page_title)
{
    var mywindow = window.open('', 'Appointment Details', 'height=400,width=600');
    mywindow.document.write('<html><head>');

    mywindow.document.write('<link rel="stylesheet" href="styles/styles.css" type="text/css" />');
    mywindow.document.write('<link rel="stylesheet" href="bs/css/bootstrap.css" type="text/css" />');
    mywindow.document.write('</head><body >');
    mywindow.document.write("<h3>" + page_title + "</h3>");
    mywindow.document.write(data);
    mywindow.document.write('</body></html>');

    mywindow.document.close(); // necessary for IE >= 10
    mywindow.focus(); // necessary for IE >= 10

    mywindow.print();
    mywindow.close();

    return true;
}