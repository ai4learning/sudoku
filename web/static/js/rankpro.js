// JavaScript Document
(function () {
var mamture_progress = $('#mamture_progress');
var progress_content = $('#progress_content');
var l = 0;
var _number = progress_content.attr('data-progress');
var timer = null;
var pro = 0;
if (_number < 1600) {
lad(_number, 1600, '.progress-box-1', function () {
    $('.progress-box-1').append('<span>再获得' + (1600 - _number) + '积分即可晋级</span>');
    $('#grade-pre1').fadeIn(400);
    $('.default').css("display", "none");
});
};
if (_number >= 1600 && _number < 2600) {
lad(1600, 1600, '.progress-box-1', function () {
    mamture_progress.addClass('v1');
    lad(_number - 1600, 2600 - 1600, '.progress-box-2', function () {
        $('.progress-box-2').addClass('active');
        $('.progress-box-2').append('<span>再获得' + (2600 - _number) + '积分即可晋级</span>');
        $('#grade-pre2').fadeIn(400);
        $('.default').css("display", "none");
    });
});
};
if (_number >= 2600 && _number < 3500) {
lad(1600, 1600, '.progress-box-1', function () {
    mamture_progress.addClass('v1')
    lad(2600, 2600, '.progress-box-2', function () {
        mamture_progress.addClass('v2');
        lad(_number - 2600, 3500 - 2600, '.progress-box-3', function () {
            $('.progress-box-3').addClass('active');
            $('.progress-box-3').append('<span>再获得' + (3500 - _number) + '积分即可晋级</span>');
            $('#grade-pre3').fadeIn(400);
            $('.default').css("display", "none");
        })
    });
});
};
if (_number >= 3500 && _number < 5000) {
lad(1600, 1600, '.progress-box-1', function () {
    mamture_progress.addClass('v1')
    lad(2600, 2600, '.progress-box-2', function () {
        mamture_progress.addClass('v2')
        lad(3500, 3500, '.progress-box-3', function () {
            mamture_progress.addClass('v3')
            lad(_number - 3500, 5000 - 3500, '.progress-box-4', function () {
                $('.progress-box-4').addClass('active');
                $('.progress-box-4').append('<span>再获得' + (5000 - _number) + '积分即可晋级</span>');
                $('#grade-pre4').fadeIn(400);
                $('.default').css("display", "none");
            })
        })
    });
});
};
if (_number >= 5000 && _number < 8000) {
lad(1600, 1600, '.progress-box-1', function () {
    mamture_progress.addClass('v1')
    lad(2600, 2600, '.progress-box-2', function () {
        mamture_progress.addClass('v2')
        lad(3500, 3500, '.progress-box-3', function () {
            mamture_progress.addClass('v3')
            lad(5000, 5000, '.progress-box-4', function () {
                mamture_progress.addClass('v4')
                lad(_number - 5000, 8000 - 5000, '.progress-box-5', function () {
                    $('.progress-box-5').addClass('active');
                    $('.progress-box-5').append('<span>再获得' + (8000 - _number) + '积分即可晋级</span>');
                    $('#grade-pre5').fadeIn(400);
                    $('.default').css("display", "none");
                })
            })
        });
    });
});
};
if (_number >= 8000 && _number < 12000) {
lad(1600, 1600, '.progress-box-1', function () {
    mamture_progress.addClass('v1')
    lad(2600, 2600, '.progress-box-2', function () {
        mamture_progress.addClass('v2')
        lad(3500, 3500, '.progress-box-3', function () {
            mamture_progress.addClass('v3')
            lad(5000, 5000, '.progress-box-4', function () {
                mamture_progress.addClass('v4')
                lad(8000, 8000, '.progress-box-5', function () {
                    mamture_progress.addClass('v5')
                    lad(_number - 8000, 12000 - 8000, '.progress-box-6', function () {
                        $('.progress-box-6').addClass('active');
                        $('.progress-box-6').append('<span>再获得' + (12000 - _number) + '积分即可晋级</span>');
                        $('#grade-pre6').fadeIn(400);
                        $('.default').css("display", "none");
                    })
                })
            });
        });
    });
});
};
if (_number >= 12000 && _number < 30000) {
lad(1600, 1600, '.progress-box-1', function () {
    mamture_progress.addClass('v1')
    lad(2600, 2600, '.progress-box-2', function () {
        mamture_progress.addClass('v2')
        lad(3500, 3500, '.progress-box-3', function () {
            mamture_progress.addClass('v3')
            lad(5000, 5000, '.progress-box-4', function () {
                mamture_progress.addClass('v4')
                lad(8000, 8000, '.progress-box-5', function () {
                    mamture_progress.addClass('v5')
                    lad(12000, 12000, '.progress-box-6', function () {
                        mamture_progress.addClass('v6')
                        lad(_number - 12000, 30000 - 12000, '.progress-box-7', function () {
                            $('.progress-box-7').addClass('active');
                            $('.progress-box-7').append('<span>再获得' + (30000 - _number) + '分即为状元</span>');
                            $('#grade-pre7').fadeIn(400);
                            $('.default').css("display", "none");
                        })
                    })
                });
            });
        });
    });
});
};

/*
@number : 成长值
@max : 最大值
@callback : 回调方法
*/
function lad(number, max, cls, callback) {
l = 0;
timer = setInterval(function () {
    if (number <= 1600) {
        l++;
    } else if (number > 1600 && number <= 2600) {
        l += 50;
    } else if (number > 2600 && number <= 3500) {
        l += 70;
    } else if (number > 3500 && number <= 5000) {
        l += 130;
    } else if (number > 5000 && number <= 8000) {
        l += 160;
    } else if (number > 8000 && number <= 12000) {
        l += 180;
    } else if (number > 12000 && number <= 30000) {
        l += 200;
    } else {
        l += 300;
    };

    pro = (l / max) * 50;
    if (l >= number) {
        clearInterval(timer);
        if (callback) callback(); //回调
    };
    $(cls).css({
        width: pro + 'px'
    })
}, 1)
}
})();