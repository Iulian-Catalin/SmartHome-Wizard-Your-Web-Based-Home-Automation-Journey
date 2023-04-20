function newToDo() {
    var name = document.getElementById('name').value;
    var urlEnc = encodeURI('additem?itemname=' + name)
    $.ajax({
        url: urlEnc
    }).done(function (response) {
        location.href = "listMyStuff.jsp";
    });
}

function loadToDo() {
    $.ajax({
        url: 'listitem'
    }).done(function (response) {
        //  printOnDiv(response.listFromBackend);
        display(response.listFromBackend);
    });
}

// function deleteAll() {
//     $.ajax({
//         url: 'manageMyToList?action=DELETE'
//     }).done(function (response) {
//         printOnDiv(response.listFromBackend); // ne vom asigura ca din backend ne vine listFromBackend goala
//     });
// }

function display(lista) {
    var randuri = "";
    lista.forEach(function (obiect) {
        randuri += "<tr>" +
            "<td>" + obiect.itemName + "</td>" +
            "<td>" + obiect.itemDate + "</td>" +
            // "<td> <a href='neverforget?action=delete&id="+obiect.id+"'>x</a></td>" +
            "</tr>";
    });
    $("#obiect").html(randuri);
}

function search(myText) {
    $.ajax("listitem", {
        cache: false,
        dataType: "json",
        data: {
            // order: ordinea,
            search: myText
        }
    }).done(function (response) {
        display(response.listFromBackend);
    });
}


function printOnDiv(listFromBackend) {
    var listHtml = '';

    var list = document.getElementById('listOfToDo');

    for (var i = 0; i < listFromBackend.length; i++) {
        var elemC = listFromBackend[i];
        var el = '<li>' + elemC.itemName + ' ' + elemC.itemDate + '</li>';
        listHtml = listHtml + el;
    }
    list.innerHTML = '<ol>' + listHtml + '</ol>';
}


