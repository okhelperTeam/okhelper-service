$.views.settings.allowCode(true);
$.views.converters("getResponseModelName", function (val) {
    return getResponseModelName(val);
});

var tempBody = $.templates('#temp_body');
var tempBodyResponseModel = $.templates('#temp_body_response_model');
//获取context path
var contextPath = getContextPath();

function getContextPath() {
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0, index + 1);
    return result;
}

$(function () {
    $.ajax({
        url: "v2/api-docs",
// 	        url : "http://petstore.swagger.io/v2/swagger.json",
        dataType: "json",
        type: "get",
        async: false,
        success: function (data) {
            var options = {
                withQuotes: true
            };
            //layui init
            layui.use(['layer', 'jquery', 'element'], function () {
                var $ = layui.jquery, layer = layui.layer, element = layui.element;
            });
            var jsonData = eval(data);
            contextPath = "http://" + jsonData.host;

            $("#title").html(jsonData.info.title);
            $("body").html($("#template").render(jsonData));

            $("[name='a_path']").click(function () {
                var path = $(this).attr("path");
                var method = $(this).attr("method");
                var operationId = $(this).attr("operationId");
                $.each(jsonData.paths[path], function (i, d) {
                    if (d.operationId == operationId) {
                        d.path = path;
                        d.method = method;
                        $("#path-body").html(tempBody.render(d));
                        var modelName;
                        if (d.responses["200"] != null) {
                            modelName = getResponseModelName(d.responses["200"]["schema"]["$ref"]);
                        } else if (d.responses["201"] != null) {
                            modelName = getResponseModelName(d.responses["201"]["schema"]["$ref"]);
                        }
                        if (modelName) {
                            var result = jiexi(jsonData.definitions[modelName], jsonData);
                            $("#path-body-response-model").jsonViewer(result, options);
                        }

                        var errors = {};
                        var error400 = "";
                        $.each(d.responses, function (i2, d2) {
                            var reg = /^2/;
                            if (!reg.test(i2)) {
                                if (i2 == '401') {
                                    d2.description = "未登录 / 验证失败";
                                } else if (i2 == "403") {
                                    d2.description = "权限不足拒绝访问";
                                } else if (i2 == "404") {
                                    d2.description = "资源未找到";
                                }

                                if (i2 == '400') {
                                    error400 = d2.description;
                                }

                                if (error400 == "") {
                                    errors[400] = {"description": "参数错误"};
                                } else {
                                    errors[400] = {"description": "参数错误 / " + error400};
                                }

                                errors[i2] = d2;

                                errors[405] = {"description": "请求方法不对"};
                                errors[409] = {"description": "资源已存在，创建失败"};
                                errors[500] = {"description": "服务器错误请请及时联系后台管理员"};
                            }
                        })

                        $("#path-body-response-model2").html(tempBodyResponseModel.render(errors));
                        // $("#path-body-response-model2").html(tempBodyResponseModel.render(d.responses));

                    }
                });


            });

            //提交测试按钮
            $("[name='btn_submit']").click(function () {
                var operationId = $(this).attr("operationId");
                var parameterJson = {};
                $("input[operationId='" + operationId + "']").each(function (index, domEle) {
                    var k = $(domEle).attr("name");
                    var v = $(domEle).val();
                    parameterJson.push({k: v});
                });
            });
        }
    });

});


function getResponseModelName(val) {
    if (!val) {
        return null;
    }
    return val.substring(val.lastIndexOf("/") + 1, val.length);
}

//测试按钮，获取数据
function getData(operationId) {
    var path = contextPath + $("[m_operationId='" + operationId + "']").attr("path");
    //path 参数
    $("[p_operationId='" + operationId + "'][in='path']").each(function (index, domEle) {
        var k = $(domEle).attr("name");
        var v = $(domEle).val();
        if (v) {
            path = path.replace("{" + k + "}", v);
        }
    });

    //请求方式
    var parameterType = $("#content_type_" + operationId).val();

    //query 参数
    var parameterJson = {};
    if ("form" == parameterType) {
        $("[p_operationId='" + operationId + "']").each(function (index, domEle) {
            var k = $(domEle).attr("name");
            var v = $(domEle).val();
            if (v) {
                parameterJson[k] = v;
            }
        });
    } else if ("json" == parameterType) {
        var str = $("#text_tp_" + operationId).val();
        try {
            parameterJson = JSON.parse(str);
        } catch (error) {
            layer.msg("" + error, {icon: 5});
            return false;
        }
    }


    //发送请求
    $.ajax({
        type: $("[m_operationId='" + operationId + "']").attr("method"),
        url: path,
        data: parameterJson,
        dataType: 'json',
        success: function (data) {
            var options = {
                withQuotes: true
            };
            $("#json-response").jsonViewer(data, options);
        }
    });
}


//请求类型
function changeParameterType(el) {
    var operationId = $(el).attr("operationId");
    var type = $(el).attr("type");
    $("#content_type_" + operationId).val(type);
    $(el).addClass("layui-btn-normal").removeClass("layui-btn-primary");
    if ("form" == type) {
        $("#text_tp_" + operationId).hide();
        $("#table_tp_" + operationId).show();
        $("#pt_json_" + operationId).addClass("layui-btn-primary").removeClass("layui-btn-normal");
    } else if ("json" == type) {
        $("#text_tp_" + operationId).show();
        $("#table_tp_" + operationId).hide();
        $("#pt_form_" + operationId).addClass("layui-btn-primary").removeClass("layui-btn-normal");
    }
}


function jiexi(jsonData, jsonall) {
    var myjson = {};
    for (var a in jsonData.properties) {

        if (jsonData.properties[a].$ref == null) {
            if (jsonData.properties[a].type == 'array') {
                if (jsonData.properties[a].items.$ref != null) {
                    var responseModelName2 = getResponseModelName(jsonData.properties[a].items.$ref);
                    var jiexi2 = jiexi(jsonall.definitions[responseModelName2], jsonall);
                    myjson[a] = [jiexi2, {"........": "........"}]
                } else {
                    myjson[a] = ["", "", "......."]
                }
            }
            else if (jsonData.properties[a].type == 'object') {
                myjson[a] = {}
            }
            else {
                myjson[a] = ""
            }
        }
        else {
            var responseModelName = getResponseModelName(jsonData.properties[a].$ref);
            var jiexi1 = jiexi(jsonall.definitions[responseModelName], jsonall);
            myjson[a] = jiexi1
        }

    }
    return myjson;
}
