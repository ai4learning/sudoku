//共用方法
function MessageUtils() {
}
MessageUtils.prototype = {};

var taskStateMap = {
    '1': '待执行',
    '2': '已执行'
};

/**
 * 任务执行状态
 * @param state 任务状态
 */
MessageUtils.taskStateFormatter = function (state) {
    return taskStateMap[cellvalue] || '';
};


/**
 * 继承方法
 * @param subClass  子类
 * @param superClass  父类
 */
MessageUtils.extend = function (subClass, superClass) {
    var F = function () {
    };
    F.prototype = superClass.prototype;
    subClass.prototype = new F();
    subClass.prototype.constructor = subClass;
    subClass.superclass = superClass.prototype; //加多了个属性指向父类本身以便调用父类函数
    if (superClass.prototype.constructor == Object.prototype.constructor) {
        superClass.prototype.constructor = superClass;
    }
};

/**
 * 打开导航菜单
 * @param navId 父菜单ID
 * @param elementId 当前也菜单ID
 */
MessageUtils.activeNavBar = function (navId, elementId) {
    var cas = navId + ">ul";
    $("#" + navId).addClass("open");
    $("#" + cas).css("display", "block");
    $("#" + elementId).addClass("active");
};

/**
 * 初始话操作按钮
 * @param selector 选择器
 * @param rowId 行ID
 * @param op       操作
 */
MessageUtils.initOpBtn = function (selector, rowId, op) {
    if (op) {
        var btn = "<div class='btn-group'>" +
            "<button type='button' class='btn btn-default btn-xs'>操作</button>" +
            "<button type='button' class='btn btn-default btn-xs dropdown-toggle' data-toggle='dropdown'>" +
            "<span class='caret'></span><span class='sr-only'></span></button><ul class='dropdown-menu pull-right' role='menu'>"
            + op +
            "</ul></div>";

        jQuery(selector).jqGrid('setRowData', rowId, {act: btn});
    }
};

/**
 * 重新载入当前页面
 * @param table
 */
MessageUtils.reloadCurrentPage = function (table) {
    var selector = table;
    if (table.indexOf("#") == -1) {
        selector = "#" + table;
    }
    var currentPage = $(selector).jqGrid("getGridParam", "page");
    $(selector).trigger("reloadGrid", [
        {page: currentPage}
    ]);
};


/**
 *隐藏Modal 并刷新父页面
 * @param modalId Modal ID
 * @param tableId  表格ID
 */
MessageUtils.hideAndReload = function (modalId, tableId) {
    var id = tableId || "message-table-list";
    $("#" + modalId).modal("hide");
    this.reloadCurrentPage(id);
};



/**
 *
 * @param url    请求的url
 * @param formId    form表单ID
 * @param modalId   当前modalIDUser
 * @param tableId   确认提示信息
 */
MessageUtils.postFormData = function (url, formId, modalId, tableId, trim, callback) {
    var trim = trim || false;
    //确认提示
    var table = tableId || "message-table-list";
    var progress = dialog(progressOption);
    $.ajax({
        type: 'POST',
        datatype: 'json',
        url: url,
        data: MessageUtils.buildParams(formId, trim),
        beforeSend: function () {
            progress.showModal()
        },
        complete: function () {
            progress.close().remove()
        },
        success: function (data) {
            progress.close().remove();
            if (data.status == "200") {
                //打算添加callback方法
                if (callback && callback instanceof Function) {
                    callback(data);
                    return;
                }
                MessageUtils.hideAndReload(modalId, table)
            } else if (data.status == "300") {
                MessageUtils.onException(data);
            }
        }
    });
};

MessageUtils.buildParams = function (formId, trim) {
    if (!trim) {
        return $('#' + formId).serialize();
    }

    var params = {};
    var fields = $('#' + formId).serializeArray();
    $.each(fields, function (i, field) {
        var name = field.name;
        var value = field.value;

        if (value) {
            if (!params[name]) {
                params[name] = $.trim(value);
            } else {
                if (!(params[name] instanceof Array)) {
                    var first = params[name];
                    params[name] = [];
                    params[name].push(first);
                }
                params[name].push($.trim(value));
            }
        }
    });

    return params;
}

MessageUtils.getParams = function (jArray) {
	 var params = {};
     var formData = jArray.serializeArray();
     for (var i = 0; i < formData.length; i++) {
         var name = formData[i]["name"];
         var value = formData[i]["value"];
         params[name] = $.trim(value);
     }

    return params;
}

/**
 * 表单中的数据在提交前可以修改
 * @param url    请求的url
 * @param formParams    form表单中需要提交的数据项
 * @param modalId   当前modalIDUser
 * @param tableId   确认提示信息
 */
MessageUtils.postFormDataWithFix = function (url, formParams, modalId, tableId) {
    //确认提示
    var table = tableId || "message-table-list";
    var progress = dialog(progressOption);
    $.ajax({
        type: 'POST',
        datatype: 'json',
        url: url,
        data: formParams,
        beforeSend: function () {
            progress.showModal()
        },
        complete: function () {
            progress.close().remove()
        },
        success: function (data) {
            progress.close().remove();
            if (data.status == "200") {

                MessageUtils.hideAndReload(modalId, table)
            } else if (data.status == "300") {
                MessageUtils.onException(data);
            }
        }
    });
};


/**
 * 删除
 * @param reqUrl 删除请求url
 * @param table 需要刷新的table名称
 */
MessageUtils.del = function (reqUrl, table) {
    MessageUtils.confirm("确定删除吗？", function () {
        $.ajax({
            global: false,
            type: 'get',
            dataType: "json",// 返回json格式的数据
            contentType: "application/json;charset=utf-8",
            url: reqUrl,
            success: function (data) {
                if (data.status == "200") {
                    MessageUtils.reloadCurrentPage(table);
                } else if (data.status == "300") {
                    MessageUtils.onException(data);
                }
            }
        });
    });
};

/**
 * 删除
 * @param reqUrl 删除请求url
 * @param data 删除请求携带的数据
 * @param table 需要刷新的table名称
 */
MessageUtils.delByData = function (reqUrl, data, table) {
    MessageUtils.confirm("确定删除吗？", function () {
        $.ajax({
            global: false,
            type: 'get',
            dataType: "json",// 返回json格式的数据
            contentType: "application/json;charset=utf-8",
            url: reqUrl,
            data: data,
            success: function (data) {
                if (data.status == "200") {
                    MessageUtils.reloadCurrentPage(table);
                } else if (data.status == "300") {
                    MessageUtils.onException(data);
                }
            }
        });
    });
};


/**
 * 更改权限
 * @param url 更改权限url
 * @param table table名称
 */
MessageUtils.alterPerm = function (url, table) {
    $.ajax({
        global: false,
        type: 'get',
        dataType: "json",// 返回json格式的数据
        contentType: "application/json;charset=utf-8",
        url: url,
        success: function (data) {
            if (data.status == "200") {
                MessageUtils.reloadCurrentPage(table);
            } else {
                MessageUtils.onException(data);
            }
        }
    });
};

/**
 *  更改权限
 * @param url 更改权限url
 * @param table 表名称
 * @param params 参数
 */

MessageUtils.postPerm = function (url, table, params) {
    $.ajax({
        global: false,
        type: 'POST',
        data: params,
        dataType: "json",// 返回json格式的数据
        //contentType: "application/json;charset=utf-8",
        url: url,
        success: function (data) {
            if (data.status == "200") {
                MessageUtils.reloadCurrentPage(table);
            } else {
                MessageUtils.onException(data);
            }
        }
    });
};

/**
 * 获取行数据
 * @param rowId
 * @param selector
 * @returns {*|jQuery}
 */
MessageUtils.getRowData = function (rowId, selector) {
    var selectorId = selector || "#message-table-list";
    return $(selectorId).jqGrid("getRowData", rowId);
};


MessageUtils.getId = function (rowId, tableId) {
    tableId = tableId || "message-table-list";
    return MessageUtils.getRowData(rowId, "#" + tableId).id;
}

MessageUtils.status = function (rowId, tableId) {
    tableId = tableId || "message-table-list";
    return MessageUtils.getRowData(rowId, "#" + tableId).status;
}

MessageUtils.isAdminRole = function () {
    var tag;
    $.ajax({
        type: 'POST',
        async: false,
        dataType: 'json',
        url: BASE_PATH + '/user/isAdmin.do',
        success: function (data) {
            if (data.statusCode != 300) {
                tag = data.result;

            } else {
                MessageUtils.onException(data);
            }
        }
    });
    return tag;
};

MessageUtils.isOperRole = function () {
    var tag;
    $.ajax({
        type: 'POST',
        async: false,
        dataType: 'json',
        url: BASE_PATH + '/user/isOper.do',
        success: function (data) {
            if (data.statusCode != 300) {
                tag = data.result;

            } else {
                MessageUtils.onException(data);
            }
        }
    });
    return tag;
};

MessageUtils.getRole = function () {
    var role;
    $.ajax({
        type: 'POST',
        async: false,
        dataType: 'json',
        url: BASE_PATH + '/user/role.do',
        success: function (data) {
            if (data.statusCode != 300) {
                role = data.result;
            } else {
                MessageUtils.onException(data);
            }
        }
    });
    return role;
};

/**
 *
 * @param scheduleId
 * @param id html元素id
 * @returns {*}
 */
MessageUtils.getScheduleInstances = function (scheduleId, id) {
    if ((typeof(scheduleId) == "undefined")) {
        return;
    }
    id = id || "instanceIdForLog";
    $.ajax({
        type: 'POST',
        async: false,
        dataType: 'json',
        url: BASE_PATH + '/schedule/instance.do',
        data: {
            scheduleId: scheduleId
        },
        success: function (data) {
            var results = data.result;
            if (results != null && data.statusCode != 300) {
                var obj = $('#' + id);
                var selectedInstanceId = obj.val();
                obj.empty();
                results.forEach(function (result) {
                    var option = new Option(result.instanceId, result.instanceId);
                    obj.append(option);
                });
                if (selectedInstanceId)
                    obj.val(selectedInstanceId);
            } else {
                MessageUtils.onException(data);
            }
        }
    });
};

/**
 *
 * @param scheduleId
 * @returns {*}
 */
MessageUtils.getScheduleStatus = function () {
    var scheduleId = $("#scheduleId").val();
    var url = BASE_PATH + '/schedule/' + scheduleId + '/list.do';
    var scheduleStatus = '';
    $.ajax({
        url: url,
        global: false,
        async: false,
        type: "POST",
        data: {},
        success: function (data) {
            if (data.status == 200) {
                var schedules = data.result;

                // 判断是否存在scheduleId
                if (schedules.length > 0) {
                    scheduleStatus = schedules[0].status;
                }

            } else {
                MessageUtils.onException(data);
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            return null;
        }
    });
    return scheduleStatus
};

/**
 * 获取代码
 * @param rowId
 * @param tableSelector
 * @returns {number|Number|.rules.code|*|string|code}
 */
MessageUtils.getCode = function (rowId, tableSelector) {
    var selector = tableSelector || "#message-table-list";
    return MessageUtils.getRowData(rowId, selector).code;
};

/**
 * 更新页脚图标
 */
MessageUtils.updatePagerIcons = function () {
    var replacement =
    {
        'ui-icon-seek-first': 'icon-double-angle-left bigger-140',
        'ui-icon-seek-prev': 'icon-angle-left bigger-140',
        'ui-icon-seek-next': 'icon-angle-right bigger-140',
        'ui-icon-seek-end': 'icon-double-angle-right bigger-140'
    };
    $('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function () {
        var icon = $(this);
        var $class = $.trim(icon.attr('class').replace('ui-icon', ''));

        if ($class in replacement) icon.attr('class', 'ui-icon ' + replacement[$class]);
    })
};

MessageUtils.resetElement = function (selId, emptyText, callback) {
    $(selId).html("");
    emptyText && $(selId).append($("<option value =\"\">").text(emptyText));
    callback && callback(this);
}

/**
 * 重写Date格式化方法
 * @param format
 * @returns {*}
 */
Date.prototype.format = function (format) {
    var date = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S+": this.getMilliseconds()
    };
    if (/(y+)/i.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
    }
    for (var k in date) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1
                ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
        }
    }
    return format;
};

/**
 * 时间格式化 默认格式化为 yyyy-MM-dd hh:mm:ss
 * @param cellvalue
 * @param options
 * @returns {*}
 */

MessageUtils.dateTimeFormatter = function (cellvalue, options) {
    var format = options.colModel.dateTimeFormat || "yyyy-MM-dd hh:mm:ss";
    var time = new Date(parseFloat(cellvalue));
    return time.format(format)
};

/**
 * 字符截取
 * @param cellvalue
 * @returns {string}
 */
MessageUtils.subFormatter = function (cellvalue) {
    return cellvalue.substr(0, 60) + "...";
};

/**
 * 枚举格式化
 * @param cellvalue 单元格值
 * @param options 选项
 * @returns {*}
 */
MessageUtils.enumFormatter = function (cellvalue, options) {
    var value = options.colModel.enums[cellvalue];
    return value || "";
};

/**
 * 布尔值格式化
 * @param cellvalue 单元格
 * @param options 选项
 * @param rowObject 行记录
 * @returns {*}
 */
MessageUtils.booleanFormatter = function (cellvalue, options, rowObject) {
    var enums = {"true": "是", "false": "否"};
    return enums[cellvalue];
}

/**
 * 启用禁用状态格式化
 * @param cellvalue
 * @param options
 * @returns {*}
 */
MessageUtils.stateFormatter = function (cellvalue, options) {
    var value = options.colModel.enums[cellvalue];
    if (value == null) {
        value = "";
    } else if (value == "已启用") {
        value = "<span class='label label-success'>已启用</span>";
    } else {
        value = "<span class='label label-warning'>已禁用</span>";
    }
    return value;
};

/**
 * 按条件查询
 * @param cnd 条件
 * @param tableId   表格
 */
MessageUtils.findByCnd = function (cnd, tableId) {
    tableId = tableId || "message-table-list";
    var tableSelector = "#" + tableId;
    var postData = $(tableSelector).jqGrid("getGridParam", "postData");
    $.extend(postData, cnd);
    $(tableSelector).jqGrid("setGridParam", {search: true}).trigger("reloadGrid", []);
};

/**
 * 初始化远程加载
 * @param id
 */
MessageUtils.initRemoteLoad = function (id) {
    $("#" + id).on('click', function (e) {
        e.preventDefault();
        var url = $(this).attr("remote-url");
        var modal = $(this).attr("remote-target");
        $(modal).load(url);
    });
};

/**
 * 初始化远程加载
 * @param id
 */
MessageUtils.initRemoteLoadExpand = function (selector) {
    if ($("#" + selector).length > 0) {
        MessageUtils.initRemoteLoad(selector);
        return;
    }
    $("." + selector).each(function (i) {
        $(this).on('click', function (e) {
            e.preventDefault();
            var url = $(this).attr("remote-url");
            var modal = $(this).attr("remote-target");
            $(modal).load(url);
        });
    })
};


/**
 * 构建自动补全
 * @param labelEl
 * @param valueEl
 * @param url
 * @param appendTo
 * @param onSelect
 */
MessageUtils.buildAutoComplete = function (labelEl, valueEl, url, appendTo, onSelect) {
    var jLabelEl = $("#"+labelEl);
    var jValueEl = $("#"+valueEl);
    $("#" + labelEl).autocomplete({
        appendTo: appendTo,
        source: url,
        select: function (event, ui) {
            event.preventDefault();
            var item = ui.item;
            jLabelEl.val(item.label);
            jValueEl.val(item.value);
            jLabelEl.data("pre_label", item.label);
            jLabelEl.data("pre_value", item.value);
            onSelect && onSelect(event, ui);
        },
        focus: function (event, ui) {
            event.preventDefault();
        },
        change: function (event, ui) {
            var label = jLabelEl.val();
            var pre_label = jLabelEl.data("pre_label");
            var pre_value = jLabelEl.data("pre_value");
            event.preventDefault();

            if ($.trim(label) == "") {
                jValueEl.val("");
                jLabelEl.data("pre_label", "");
                jLabelEl.data("pre_value", "");
                onSelect && onSelect(event, ui);
                return;
            }
            if (label != pre_label) {
                //序列化一个参数
                $.ajax({
                    url: url,
                    data: {term: label}
                }).done(function (items) {
                    if (items && items.length >= 1) {
                        for (var x in items) {
                            if (items[x].label == label) {
                                jLabelEl.data("pre_label", items[x].label);
                                jLabelEl.data("pre_value", items[x].value);
                                jLabelEl.val(items[x].label);
                                jValueEl.val(items[x].value);
                                break;
                            }
                        }
                    } else {
                        jValueEl.val("");
                        jLabelEl.data("pre_label", "");
                        jLabelEl.data("pre_value", "");
                    }
                    onSelect && onSelect(event, ui);
                })
            }
        }
    });
}

/**
 * 构造远程数据加载按钮
 * @param btnId 按钮ID
 * @param modalId 页面显示模态框
 * @param url url
 * @param style 按钮样式
 * @param label 按钮名称
 * @returns {string}
 */
MessageUtils.createRemoteModalUrl = function (btnId, modalId, url, style, label) {
    return "<li>" +
        "<a id='" + btnId + "' href='#" + modalId + "' role='button' class='remoteModal' data-toggle='modal' remote-url='" + url + "' remote-target='#" + modalId + "'>" +
        "<i class='" + style + "'> </i> " +
        "<span class='menu-span'>" + label + "</span>" +
        "</a>" +
        "</li>";
};

MessageUtils.createLocalUrl = function (btnId, id, style, label) {
    return "<li>" +
        "<a id='" + btnId + "' href='#' role='button' class='remoteModal' data-toggle='modal' taskId='" + id + "' >" +
        "<i class='" + style + "'> </i> " +
        "<span class='menu-span'>" + label + "</span>" +
        "</a>" +
        "</li>";
};
/**
 * 确定提示框
 * @param title
 * @param okCallback
 */

MessageUtils.confirm = function (title, okCallback) {
  dialog({
        title: '提示',
        content: title,
        okValue: '确定',
        ok: okCallback,
        cancelValue: '取消',
        cancel: function () {
        },
        zIndex: 1041  //大于bootstrap的1040
    }).width(320).showModal();
};

/**
 * 错误提示框
 * @param error 错误信息
 */
MessageUtils.errorAlert = function (error) {
    dialog({
        title: '错误提示',
        content: error,
        okValue: '确定',
        ok: function () {
        },
        zIndex: 1041     //大于bootstrap的1040
    }).width(320).show();
};

/**
 * 消息提示框
 * @param info 提示消息
 */
MessageUtils.msgAlert = function (info) {
    dialog({
        title: '提示',
        content: info,
        okValue: '确定',
        ok: function () {
        },
        zIndex: 1041     //大于bootstrap的1040
    }).width(320).show();
};

/**
 * 成功提示框
 * @param info 提示消息
 */
MessageUtils.msgSuccess = function (info, callback) {
    var d = dialog({
        title: '提示',
        content: info,
        okValue: '确定',
        ok: function () {
            callback && callback();
        },
        zIndex: 1041     //大于bootstrap的1040
    }).width(320).show();
    d.onclose = function () {
        callback && callback();
    }
};


/**
 * 表单校验
 * @param formId
 * @returns {*|jQuery}
 */
MessageUtils.isValid = function (formId) {
    var selector = "#" + formId;

    $(selector).validator({
        ignore: '.ignored' //将对加了ignored的class元素不进行校验
    });
    $(selector).trigger("validate");
    return $(selector).isValid()
};

/**
 * 异常输出
 * @param data 后台返回的数据
 */
MessageUtils.onException = function (data) {
    var info = "操作失败，请稍候再试!";
    if (data.error) {
        info = data.error;
    } else if (data.message) {
        info = data.message;
    }
    MessageUtils.errorAlert(info);
};


//视图公共部分
var CAPBaseView = function (module, name) {
    this.module = module;
    this.name = name;
};

CAPBaseView.prototype = {
    modal_common: "commonModal",
    modal_add: "addModal",
    modal_edit: "editModal",
    form_update: "updateForm",
    form_add: "addForm",
    form_audit: "auditForm",
    table_id: "message-table-list",
    pager_id: "cap-pager",
    state_enabled: "<span class='label label-success'>已启用</span>",
    state_disabled: "<span class='label label-warning'>已禁用</span>",
    state_passed: "<span class='label label-success'>审核通过</span>",
    state_refuse: "<span class='label label-important'>审核未通过</span>",
    state_audit: "<span class='label label-warning'>待审核</span>",
    state_succeed: "<span class='label label-info'>创建成功</span>",
    state_filed: "<span class='label label-danger'>创建失败</span>",
    state_new: "<span class='label label-success'>新增</span>",

    getName: function () {
        return this.name;
    },
    getModule: function () {
        return this.module;
    },
    //打开导航菜单
    bindNavBar: function () {
        //具体视图自己实现
    },
    //绑定搜索事件
    bindSearchClick: function () {
        var that = this;
        $("#btn_search").click(function () {
            that.onSearchClick()
        });
    },
    //绑定新增提交事件
    bindAddClick: function () {
        var that = this;
        $("#btn_add").click(function () {
            that.onAddClick();
        });
    },//绑定新增提交事件
    bindNewClick: function () {
        var that = this;
        $("#btn_new").click(function () {
            that.onNewClick()
        });
    },
    //绑定修改提交事件
    bindUpdateClick: function () {
        var that = this;
        $("#btn_update").click(function () {
            that.onUpdateClick(this)
        });
    },
    //绑定分发数据中心事件
    bindDistributeClick: function () {
        var that = this;
        $("#btn_distribute").click(function () {
            that.onDistributeClick()
        });
    },
    //搜索提交
    onSearchClick: function () {
        var params = this.getSearchParams();
        $("#" + this.table_id).jqGrid('setGridParam', {
            datatype: "json",
            current: true,
            postData: params
        }).trigger("reloadGrid", [
            {page: 1}
        ]);
    },
    //添加提交
    onAddClick: function () {
        if (MessageUtils.isValid(this.form_add)) {
            MessageUtils.postFormData(this.getModule() + '/add.do', this.form_add, this.modal_add, this.table_id);
        }
    },
    //点击新建
    onNewClick: function () {
        var url = this.getModule() + '/add.do';
        $('#' + this.modal_add).modal({
            remote: url
        });
    },
    //修改提交
    onUpdateClick: function () {
        if (MessageUtils.isValid(this.form_update)) {
            MessageUtils.postFormData(this.getModule() + '/update.do', this.form_update, this.modal_edit, this.table_id);
        }
    },
    //点击分发数据中心
    onDistributeClick: function () {
        var url = this.getModule() + '/distribute.do';

        $('#userModal').modal({
            remote: url
        });
    },
    //初始化jqGrid 表格
    initDataTable: function ($) {
        //
        var that = this;
        $("#" + that.table_id).jqGrid({
            url: that.getSearchUrl(),
            datatype: "json",
            mtype: "POST",
            height: $(document).height() - $("#message-table-div").offset().top - 85,
            colNames: that.getColNames(),
            colModel: that.getColModel(),
            rowTotal: 2000,
            viewrecords: true,
            rowNum: 10,
            rowList: [10, 30, 100, 300],
            pager: "#" + that.pager_id,
            altRows: true,
            multiselect: false,
            multiboxonly: true,
            prmNames: { //如当前查询实体为ware，这些可以在查询对象的superObject中设定
                page: "page",
                rows: "pageSize"
            },
            postData: that.setPostData(),
            jsonReader: { //server返回Json解析设定
                root: "result", //对于json中数据列表
                page: "pagination.page",
                total: "pagination.totalPage",
                records: "pagination.totalRecord",
                repeatitems: false
            },
            loadComplete: function () {
                that.loadComplete();
            },
            autowidth: true
        });
    }, getSearchUrl: function () {
        return this.getModule() + '/search.do';
    },

    //逻辑删除
    transit: function (id) {
        var that = this;
        MessageUtils.confirm("确定删除此记录吗？", function () {
            var reqUrl = that.getModule() + '/transit.do';
            var params = {"id": id};
            MessageUtils.postPerm(reqUrl, that.table_id, params);
        });
    },
    //删除
    deleteRecord: function (id) {
        var that = this;
        MessageUtils.confirm("确定删除此记录吗？", function () {
            var reqUrl = that.getModule() + '/delete.do';
            var params = {"id": id};
            MessageUtils.postPerm(reqUrl, that.table_id, params);
        });

    },
    //启用
    enable: function (id) {
        var that = this;
        MessageUtils.confirm("确定启用此记录吗？", function () {
            var reqUrl = BASE_PATH + '/' + that.getModule() + '/enable.do';
            var params = {"id": id};
            MessageUtils.postPerm(reqUrl, that.table_id, params);
        });
    },
    //禁用
    disable: function (id) {
        var that = this;
        MessageUtils.confirm("确定禁用此记录吗？", function () {
            var reqUrl = BASE_PATH + '/' + that.getModule() + '/disable.do';
            var params = {"id": id};
            MessageUtils.postPerm(reqUrl, that.table_id, params);
        });
    },


    getColNames: function () {
        //不同视图具体实现此方法
        return "";
    },
    getColModel: function () {
        //不同视图具体实现此方法
        return "";
    },
    loadComplete: function () {
        //不同视图具体实现此方法
        MessageUtils.updatePagerIcons();
    }, setPostData: function () {
        return {};
    }
    , getSearchParams: function () {
        var params = {};
        var formData = $(".form-cnd").serializeArray();
        for (var i = 0; i < formData.length; i++) {
            var name = formData[i]["name"];
            var value = formData[i]["value"];
            params[name] = $.trim(value);
        }
        return params;
    }, createEditUrl: function (id) {
        return this.getModule() + "/update/" + id + ".do"
    }, createEnableBtn: function (id) {
        return "<li><a href='javascript:" + this.getModule() + ".enable(" + id + ")'><i class='icon-ok-circle'></i><span class='menu-span'>启用</span></a></li>"
    }, createDisableBtn: function (id) {
        return "<li><a href='javascript:" + this.getModule() + ".disable(" + id + ")'><i class='icon-ban-circle'></i><span class='menu-span'>禁用</span></a></li>";
    }, createTransitBtn: function (id) {
        return "<li><a href='javascript:" + this.getModule() + ".transit(" + id + ")'><i class='icon-trash'></i><span class='menu-span'>删除</span></a></li>";
    }, createDeleteBtn: function (id) {
        return "<li><a href='javascript:" + this.getModule() + ".deleteRecord(" + id + ")'><i class='icon-trash'></i><span class='menu-span'>删除</span></a></li>";
    }, getId: function (rowId, tableId) {
        tableId = tableId || "message-table-list";
        return MessageUtils.getRowData(rowId, "#" + tableId).id;
    },

    getUser: function (rowId, tableId) {

        tableId = tableId || "message-table-list";
        return MessageUtils.getRowData(rowId, "#" + tableId).applicant;

    },
    bindInput: function () {


        $(".seach-bar input,.seach-bar select").keypress(function (event) {


            var key = event.keyCode;
            if (window.event) {

                key = event.keyCode;

            } else if (event.which) {

                key = event.which;

            }

            if (key == 13) {

                var btn_search = $("#btn_search");
                if (btn_search)
                    btn_search.click();

            }

        });

    },

    //获取状态
    getStatus: function (rowId, tableId) {
        tableId = tableId || "message-table-list";
        return MessageUtils.getRowData(rowId, "#" + tableId).status;
    }, initManagePage: function () {
        this.initDataTable($);
        this.bindNavBar();
        this.bindSearchClick();
        this.bindNewClick();
        this.bindDistributeClick();
    }, getValue: function (rowId, tableId, propName) {
        tableId = tableId || "message-table-list";
        return MessageUtils.getRowData(rowId, "#" + tableId)[propName];
    }, initDataCenterList: function (url, ancestor) {
        $.ajax({
            global: false,
            type: "get",// 使用get方法访问后台
            dataType: "json",// 返回json格式的数据
            url: url,// 要访问的后台地址
            contentType: "application/json;charset=utf-8",
            success: function (data) {
                var result = data.result;
                if (result) {
                    ancestor = $("#" + ancestor) || $(document);
                    var obj = ancestor.find("#dataCenterId");
                    result.forEach(function (center) {
                        var addOption = new Option(center.name, center.id);//生成一个选项
                        obj.append(addOption);
                    });
                }
            }
        });
    }, bindSelect2: function (selector, url, op) {
        var _selector = selector || ".cap-select2";
        var _op = {allowClear: true};
        $.extend(_op, op);
        var images = $(_selector);
        if (typeof url == 'undefined') {
            images.select2(_op);
        } else {
            $.getJSON(url, function (data) {
                var results = data.result;
                $.each(results, function (i, item) {
                    images.append("<option value='" + item.id + "'>&nbsp;" + item.name + "</option>");
                });
            }).done(function () {
                images.select2(_op)
            });
        }
    }
};
