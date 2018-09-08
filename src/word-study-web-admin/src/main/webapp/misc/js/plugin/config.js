/**
 * Created by david on 2016/4/17.
 */
requirejs.config({
    //默认情况下模块所在目录为js/lib
    baseUrl: '/misc/js/plugin',
    //当模块id前缀为app时，他便由js/app加载模块文件
    //这里设置的路径是相对与baseUrl的，不要包含.js
    paths: {
        app: 'app1',
        conf1:'conf1/conf1',
        xheditor:'xheditor/xheditor-1.2.2.min',
        xheditor_lang:'xheditor/xheditor_lang/zh-cn',
        calendar:'calendar/calendar'
    },
    map: {
        '*': {
            'css': 'css.min'
        }
    },
    shim:{
        'conf1':{
            deps: ['css!conf1.css']
        },
        xheditor_lang:['xheditor'],
        calendar:{
            deps: ['css!calendar.css']
        }
    }

});

