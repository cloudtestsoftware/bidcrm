dhtmlXForm.prototype.items.upload = {
    render: function(a, b) {
        a._type = "up";
        a._enabled = !0;
        a._checked = !0;
        a.className = b.position + (typeof b.className == "string" ? " " + b.className : "");
        var c = document.createElement("DIV");
        a.appendChild(c);
        if (!isNaN(b.inputLeft)) c.style.left = parseInt(b.inputLeft) + "px";
        if (!isNaN(b.inputTop)) c.style.top = parseInt(b.inputTop) + "px";
        if (b.inputWidth != "auto" && !isNaN(b.inputWidth)) c.style.width = parseInt(b.inputWidth) + "px";
        a._uploader = new dhtmlXFileUploader(c, b.swfPath || "", b.swfUrl || "", b.mode ||
            null, b.swfLogs);
        a._uploader.setURL(b.url || "");
        a._uploader.callEvent = a.callEvent;
        typeof b.autoStart != "undefined" && a._uploader.setAutoStart(b.autoStart);
        typeof b.autoRemove != "undefined" && a._uploader.setAutoRemove(b.autoRemove);
        typeof b.titleScreen != "undefined" && a._uploader.enableTitleScreen(b.titleScreen);
        typeof b.titleText != "undefined" && a._uploader.setTitleText(b.titleText);
        b.hidden == !0 && this.hide(a);
        b.disabled == !0 && this.userDisable(a);
        if (!(b.inputHeight == "auto" || parseInt(b.inputHeight) == NaN)) a._uploader.p_files.style.height =
            parseInt(b.inputHeight) + "px";
        return this
    },
    destruct: function(a) {
        this.doUnloadNestedLists(a);
        a._uploader.unload();
        a._uploader = null;
        a._checked = null;
        a._enabled = null;
        a._idd = null;
        a._type = null;
        a.onselectstart = null;
        a._autoCheck = null;
        a.callEvent = null;
        a.checkEvent = null;
        for (a.getForm = null; a.childNodes.length > 0;) a.removeChild(a.childNodes[0]);
        a.parentNode.removeChild(a);
        a = null
    },
    setText: function() {},
    getText: function() {},
    enable: function(a) {
        a._enabled = !0;
        if (String(a.className).search("disabled") >= 0) a.className =
            String(a.className).replace(/disabled/gi, "");
        a._uploader.enable()
    },
    disable: function(a) {
        a._enabled = !1;
        String(a.className).search("disabled") < 0 && (a.className += " disabled");
        a._uploader.disable()
    },
    setWidth: function(a, b) {
        a.childNodes[0].style.width = b + "px";
        a._width = b
    },
    getWidth: function(a) {
        return a._width || parseInt(a.childNodes[0].style.width)
    },
    setValue: function(a) {
        a._uploader.clear()
    },
    getValue: function(a) {
        var b = a._uploader.getData(),
            c = {},
            d = 0,
            e;
        for (e in b) c[a._idd + "_r_" + d] = b[e].realName, c[a._idd + "_s_" + d] =
            b[e].serverName, d++;
        c[a._idd + "_count"] = d;
        return c
    },
    getUploader: function(a) {
        return a._uploader
    },
    getStatus: function(a) {
        return a._uploader.getStatus()
    }
};
(function() {
    for (var a in {
            doUnloadNestedLists: 1,
            isEnabled: 1
        }) dhtmlXForm.prototype.items.upload[a] = dhtmlXForm.prototype.items.checkbox[a]
})();
dhtmlXForm.prototype.setFormData_upload = function(a) {
    this.doWithItem(a, "setValue")
};
dhtmlXForm.prototype.getUploader = function(a) {
    return this.doWithItem(a, "getUploader")
};
dhtmlXForm.prototype.getUploaderStatus = function(a) {
    return this.doWithItem(a, "getStatus")
};

function dhtmlXFileUploader(a, b, c, d, e) {
    var f = this;
    if (typeof d == "string" && typeof this[d] == "function") this.engine = d;
    else if (this.engine = "html4", typeof window.FormData != "undefined" && typeof window.XMLHttpRequest != "undefined" && typeof window.XMLHttpRequestUpload != "undefined") this.engine = "html5";
    else if (typeof window.swfobject != "undefined") {
        var g = swfobject.getFlashPlayerVersion();
        if (g.major >= 10) this.engine = "flash"
    }
    typeof a == "string" && (a = document.getElementById(a));
    this._swf_file_url = b || "";
    this._swf_upolad_url =
        c || "";
    this._swf_logs = e;
    this.p = document.createElement("DIV");
    this.p.className += " dhx_file_uploader";
    a.appendChild(this.p);
    this.p_files = document.createElement("DIV");
    this.p_files.className = "dhx_upload_files";
    this.p.appendChild(this.p_files);
    this.p_controls = document.createElement("DIV");
    this.p_controls.className = "dhx_upload_controls";
    this.p.appendChild(this.p_controls);
    this._files = {};
    this._items = {};
    this._data = {};
    this._autoRemove = this._autoStart = !1;
    this._enabled = this._titleScreen = !0;
    this._uploaded_count =
        0;
    this._uid = function() {
        this._idd ? this._idd++ : this._idd = (new Date).getTime();
        return this._idd
    };
    this._initToolbar = function() {
        this.b_opts = {
            info: {
                onclick: null
            },
            browse: {
                onclick: null,
                tooltip: "Browse"
            },
            upload: {
                onclick: function() {
                    f._enabled && !f._uploading && (f._switchButton(!0), f._uploadStart())
                },
                tooltip: "Upload"
            },
            cancel: {
                onclick: function() {
                    f._enabled && (f._uploadStop(), f._switchButton(!1))
                },
                tooltip: "Stop"
            },
            clear: {
                onclick: function() {
                    f._enabled && f.clear()
                },
                tooltip: "Clear list"
            }
        };
        this.buttons = {};
        for (var a in this.b_opts) {
            var b =
                document.createElement("DIV");
            b.innerHTML = "&nbsp;";
            b.className = "dhx_file_uploader_button button_" + a;
            b.onclick = this.b_opts[a].onclick;
            if (this.b_opts[a].tooltip) b.title = this.b_opts[a].tooltip;
            this.p_controls.appendChild(b);
            this.buttons[a] = b;
            b = null
        }
        this.buttons.cancel.style.display = "none"
    };
    this._readableSize = function(a) {
        for (var b = !1, c = "b,Kb,Mb,Gb,Tb,Pb,Eb".split(","), d = 0; d < c.length; d++) a > 1024 ? a /= 1024 : b === !1 && (b = d);
        b === !1 && (b = c.length - 1);
        return Math.round(a * 100) / 100 + " " + c[b]
    };
    this._addFileToList = function(a,
        b, c, d, e) {
        this._checkTitleScreen();
        var g = document.createElement("DIV");
        g._idd = a;
        g.className = "dhx_file dhx_file_" + d;
        g.innerHTML = "<div class='dhx_file_param dhx_file_name'>&nbsp;</div><div class='dhx_file_param dhx_file_progress'>" + e + "%</div><div class='dhx_file_param dhx_file_delete' title='Remove from list'>&nbsp;</div>";
        this.p_files.appendChild(g);
        g.childNodes[0].style.width = g.offsetWidth - 127 + "px";
        this._items[a] = g;
        this._updateFileNameSize(a);
        g.childNodes[2].onclick = function() {
            if (f._enabled) {
                var a =
                    this.parentNode._idd;
                f._removeFileFromQueue(a)
            }
        }
    };
    this._removeFileFromList = function(a) {
        if (this._items[a]) this._items[a].childNodes[2].onclick = null, this._items[a].parentNode.removeChild(this._items[a]), this._items[a] = null, delete this._items[a], this._data[a] && (this._data[a] = null, delete this._data[a]), this._checkTitleScreen()
    };
    this._updateFileNameSize = function(a) {
        this._items[a].childNodes[0].innerHTML = this._files[a].name + (!isNaN(this._files[a].size) ? " (" + this._readableSize(this._files[a].size) + ")" : "&nbsp;");
        this._items[a].childNodes[0].title = this._files[a].name + (!isNaN(this._files[a].size) ? " (" + this._readableSize(this._files[a].size) + ")" : "")
    };
    this._updateFileInList = function(a, b, c) {
        if (this._items[a]) this._items[a].className = "dhx_file dhx_file_" + b, b == "uploading" && c < 100 && this._progress_type == "loader" ? (this._items[a].childNodes[1].className = "dhx_file_param dhx_file_uploading", this._items[a].childNodes[1].innerHTML = "&nbsp;") : (this._items[a].childNodes[1].className = "dhx_file_param dhx_file_progress", this._items[a].childNodes[1].innerHTML =
            c + "%"), this._updateFileNameSize(a)
    };
    this._removeFilesByState = function(a) {
        for (var b in this._files)(a === !0 || this._files[b].state == a) && this._removeFileFromQueue(b)
    };
    this._switchButton = function(a) {
        if (a == !0) this.buttons.upload.style.display = "none", this.buttons.cancel.style.display = "";
        else {
            var b = this._uploaded_count;
            this.buttons.upload.style.display = "";
            this.buttons.cancel.style.display = "none";
            this._uploaded_count = 0;
            b > 0 && this.callEvent("onUploadComplete", [b])
        }
    };
    this._uploadStart = function() {
        if (!this._uploading)
            for (var a in this._files)
                if (this._files[a].state ==
                    "fail") this._files[a].state = "added", this._updateFileInList(a, "added", 0);
        this._uploading = !0;
        var b = !1;
        for (a in this._files)
            if (!b && [this._files[a].state] == "added") b = !0, this._files[a].state = "uploading", this._updateFileInList(a, "uploading", 0), this._doUploadFile(a);
        if (!b) this._uploading = !1, this._switchButton(!1)
    };
    this._onUploadSuccess = function(a, b, c) {
        try {
            eval("var t=" + c.data)
        } catch (d) {}
        if (typeof t != "undefined" && t.state == !0 && t.name != null) b = t.name;
        this._uploaded_count++;
        this._data[a] = {
            realName: this._files[a].name,
            serverName: b
        };
        this._files[a].state = "uploaded";
        this._updateFileInList(a, "uploaded", 100);
        this.callEvent("onUploadFile", [this._files[a].name, b]);
        this._autoRemove && this._removeFileFromQueue(a);
        this._uploading && this._uploadStart()
    };
    this._onUploadFail = function(a) {
        this._files[a].state = "fail";
        this._updateFileInList(a, "fail", 0);
        this.callEvent("onUploadFail", [this._files[a].name]);
        this._uploading && this._uploadStart()
    };
    this._onUploadAbort = function(a) {
        this._uploading = !1;
        this._files[a].state = "added";
        this._updateFileInList(a,
            "added", 0);
        this.callEvent("onUploadCancel", [this._files[a].name])
    };
    this._checkTitleScreen = function() {
        var a = 0,
            b;
        for (b in this._files) a++;
        if (a == 0 && this.p.className.search("dhx_file_uploader_title") < 0 && this._titleScreen) this.p.className += " dhx_file_uploader_title", this.buttons.info.innerHTML = this._titleText, this.buttons.info.style.width = Math.max(this.p_controls.offsetWidth - 134, 0) + "px";
        if ((a > 0 || !this._titleScreen) && this.p.className.search("dhx_file_uploader_title") >= 0) this.p.className = this.p.className.replace(/dhx_file_uploader_title/g,
            ""), this.buttons.info.innerHTML = ""
    };
    this.callEvent = function() {};
    this.upload = function() {
        this._uploading || this._uploadStart()
    };
    this.setAutoStart = function(a) {
        this._autoStart = a == !0
    };
    this.setAutoRemove = function(a) {
        this._autoRemove = a == !0
    };
    this.enableTitleScreen = function(a) {
        this._titleScreen = a == !0;
        this._checkTitleScreen()
    };
    this.setTitleText = function(a) {
        this._titleText = a;
        if (this.p.className.search("dhx_file_uploader_title") >= 0) this.buttons.info.innerHTML = this._titleText
    };
    this.setURL = function(a) {
        this._url =
            a
    };
    this.setSWFURL = function(a) {
        this._swf_upolad_url = a
    };
    this.enable = function() {
        this._enabled = !0;
        this.p_files.className = "dhx_upload_files";
        this.p_controls.className = "dhx_upload_controls"
    };
    this.disable = function() {
        this._enabled = !1;
        this.p_files.className = "dhx_upload_files dhx_uploader_dis";
        this.p_controls.className = "dhx_upload_controls dhx_uploader_dis"
    };
    this.getStatus = function() {
        var a = 0,
            b;
        for (b in this._files) {
            if (this._files[b].state != "uploaded") return -1;
            a = 1
        }
        return a
    };
    this.getData = function() {
        return this._data
    };
    this.clear = function() {
        this._uploading && f._uploadStop();
        f._switchButton(!1);
        f._removeFilesByState(!0)
    };
    this.unload = function() {
        this._removeFilesByState(!0);
        this._items = this._files = this._data = null;
        this._unloadEngine();
        for (var a in this.buttons) this.buttons[a].onclick = null, this.buttons[a].parentNode.removeChild(this.buttons[a]), this.buttons[a] = null, delete this.buttons[a];
        this.buttons = null;
        for (a in this.b_opts) this.b_opts[a].onclick = null, this.b_opts[a] = null, delete this.b_opts[a];
        this.b_opts = null;
        this.p_controls.parentNode.removeChild(this.p_controls);
        this.p_controls = null;
        this.p_files.parentNode.removeChild(this.p_files);
        this.engine = this._swf_upolad_url = this._swf_file_url = this.unload = this.clear = this.getData = this.getStatus = this.disable = this.enable = this.setURL = this.setTitleText = this.enableTitleScreen = this.setAutoRemove = this.setAutoStart = this.upload = this.callEvent = this._checkTitleScreen = this._onUploadAbort = this._onUploadFail = this._onUploadSuccess = this._uploadStart = this._switchButton = this._removeFilesByState = this._updateFileInList = this._updateFileNameSize =
            this._removeFileFromList = this._addFileToList = this._readableSize = this._initToolbar = this._uid = this._idd = this._uploading = this._url = this._uploaded_count = this._titleText = this._titleScreen = this._progress_type = this._enabled = this._autoStart = this._autoRemove = this.p_files = null;
        this.p.className = this.p.className.replace(/dhx_file_uploader_title/gi, "").replace(/dhx_file_uploader/gi, "");
        f = a = this.p = null
    };
    var h = new this[this.engine],
        i;
    for (i in h) this[i] = h[i], h[i] = null;
    i = h = a = null;
    this._initToolbar();
    this._initEngine();
    this._checkTitleScreen();
    return this
}
dhtmlXFileUploader.prototype.html5 = function() {};
