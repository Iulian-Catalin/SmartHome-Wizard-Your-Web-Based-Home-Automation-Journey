function newItemToDo() {
    var name = document.getElementById('name1').value;
    var room = document.getElementById('room1').value;
    var watts = document.getElementById('watts1').value;
    var urlEnc = encodeURI('additem?itemname='+name+'&room='+room+'&watts='+watts)
    console.log(urlEnc);
    $.ajax({url:urlEnc}).done(function (response) {
        location.href = "listClientMenu.jsp";
    });
}

function newRoomToDo() {
    var name = document.getElementById('name2').value;
    var urlEnc = encodeURI('addroom?roomname=' + name)
    $.ajax({
        url: urlEnc
    }).done(function (response) {
        location.href = "listClientMenu.jsp";
    });
}

function loadItemToDo() {
    $.ajax({
        url: 'listitem'
    }).done(function (response) {
        //  printOnDiv(response.listFromBackend);
        display(response.listFromBackend);
        console.log(response.listFromBackend)
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


function display(list) {
    var rows = "";
    list.forEach(function (objectJS) {
        rows += "<tr>" +
            "<td>" + objectJS.itemName + "</td>" +
            "<td>" + objectJS.itemDate + "</td>" +
            "<td>" + objectJS.roomName + "</td>" +
            "<td>" + objectJS.watts + "</td>" +
            "<td>" + objectJS.quantity + "</td>" +
            "<td> <a style=\"text-decoration-line:none;color:darkorange;font-size: xx-large\"  href='increaseitem?idDB="+objectJS.idDB+"&qty="+objectJS.quantity+"'>&#65291</a>" +
            " <a style=\"text-decoration-line:none;color:blue;font-size: xx-large\" href='decreaseitem?idDB="+objectJS.idDB+"&qty="+objectJS.quantity+"'>&#65293</a></td>" +
            "<td>" + onToggle(objectJS.power)+"</td>" +
            "<td> <a style=\"text-decoration-line:none;color:mediumpurple\" href='poweritem?idDB="+objectJS.idDB+"&power="+objectJS.power+"'>POWER</a></td>"  +
            "</tr>";
    });
    $("#obiect1").html(rows);
}

function display2(list) {
    var rows = "";
    list.forEach(function (objectJS) {
        rows += "<tr>" +
            "<td>" + objectJS.roomName + "</td>" +
            // "<td> <a href='neverforget?action=delete&id="+obiect.id+"'>x</a></td>" +
            "</tr>";
    });
    $("#obiect2").html(rows);
}

function search(myText) {
    $.ajax("listitem", {
        cache: false,
        dataType: "json",
        data: {
            search: myText
        }
    }).done(function (response) {
        display(response.listFromBackend);
    });
}


function onToggle(power) {
    if (power === false) {
        power = "<label style=\"text-decoration-line:none;color:red\">OFF</label>";
    }else power="<label style=\"text-decoration-line:none;color:forestgreen\">ON</label>";
    return power;
}