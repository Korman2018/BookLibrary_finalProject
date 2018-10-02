
    $(document).ready(function () {
      document.getElementById('admin_tbl').onclick = function (e) {
        var e = e || window.event;
        var o = e.srcElement || e.target;
        var idB = o.parentElement.children[0];
        if (o.tagName != 'TD') return;
        location.href = 'adminBook/' + idB.innerHTML;
      }
    });