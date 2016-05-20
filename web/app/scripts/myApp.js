function getInfo(data) {
    // console.log(data);
    var url = "http://localhost/proxy.php?url=" + data.getAttribute("data-url");
    $.getJSON(url, function (data, code) {
        var colors = ['success', 'info', 'warning', 'danger', 'default'];
        if (code === "success") {
            $('#myFile').empty();
            var target = $('#myFile');
            var icon_dir = "<span class=\"glyphicon glyphicon-folder-open\"></span>";
            var icon_file = "<span class=\"glyphicon glyphicon-paperclip\"></span>";

            var tmp = data.dropbox;
            if (tmp !== undefined) {
                var tmp = tmp.contents;
                if (tmp !== underfined) {
                    // multi fichiers
                    for (var i = 0; i < tmp.length; i++) {
                        if (tmp[i].is_dir) {
                            target.append("<li class=\"list-group-item list-group-item-" + colors[i % 5] + "\">" + icon_dir + "&nbsp;" + tmp[i].path + "</li>");
                        } else {
                            target.append("<li class=\"list-group-item list-group-item-" + colors[i % 5] + "\">"
                                + icon_file + "&nbsp;" + tmp[i].path + "&nbsp;" + tmp[i].size + "</li>");
                        }
                    }
                }
                else {
                    // seul
                    target.append("<li class=\"list-group-item list-group-item-" + colors[i % 5] + "\">" + data.dropbox.path + "&nbsp;<span class=\"glyphicon glyphicon-paperclip\"></span>" + data.dropbox.size + "</li>");
                }
            }
            var google = data.google;
            if (google !== undefined) {
                target.append("<li class=\"list-group-item list-group-item-" + colors[i % 5] + "\">" + "&nbsp;" + google.title + "</li>");
            }
        }
    });
}

function rename(s) {
    console.log(s.getAttribute("data-coucou"));
    $("#from").attr("value", s.getAttribute("data-coucou"));
}

function share(data) {
    var url = "http://localhost/proxy.php?url=" + data.getAttribute("data-share");
    $.getJSON(url, function (data, code) {
        $('#shareUrl').attr('value', data.url);
    });
}