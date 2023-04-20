function newItemToDo() {
    var name = document.getElementById('name1').value;
    var room = document.getElementById('room1').value;
    var watts = document.getElementById('watts1').value;
    var urlEnc = encodeURI('additem?itemname='+name+'&room='+room+'&watts='+watts)
    console.log(urlEnc);
    $.ajax({url:urlEnc}).done(function (response) {
        location.href = "listMyStuff.jsp";
    });
}

function newRoomToDo() {
    var name = document.getElementById('name2').value;
    var urlEnc = encodeURI('addroom?roomname=' + name)
    $.ajax({
        url: urlEnc
    }).done(function (response) {
        location.href = "listMyStuff.jsp";
    });
}

function loadItemToDo() {
    $.ajax({
        url: 'listitem'
    }).done(function (response) {
        //  printOnDiv(response.listFromBackend);
        display(response.listFromBackend);
    });
}

function loadRoomToDo() {
    $.ajax({
        url: 'listroom'
    }).done(function (response) {
        //  printOnDiv(response.listFromBackend);
        display2(response.listFromBackend);
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
            "<td>" + obiect.room + "</td>" +
            "<td>" + obiect.watts + "</td>" +
            "<td>" + obiect.on + "</td>" +
            // "<td> <a href='neverforget?action=delete&id="+obiect.id+"'>x</a></td>" +
            "</tr>";
    });
    $("#obiect1").html(randuri);
}

function display2(lista) {
    var randuri = "";
    lista.forEach(function (obiect) {
        randuri += "<tr>" +
            "<td>" + obiect.roomName + "</td>" +
            // "<td> <a href='neverforget?action=delete&id="+obiect.id+"'>x</a></td>" +
            "</tr>";
    });
    $("#obiect2").html(randuri);
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


