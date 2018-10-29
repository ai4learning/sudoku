import React, {Component} from 'react'
import ReactDOM from 'react-dom'
import {Router} from 'react-router'
import { LocaleProvider } from 'antd'
import zh_CN from 'antd/lib/locale-provider/zh_CN'
import 'moment/locale/zh-cn'
import routes from './routes'
import history from './history'
import 'normalize.css'
import './style/index.less'

ReactDOM.render(
  <LocaleProvider locale={zh_CN}>
    <Router history={history}>
      {routes}
    </Router>
  </LocaleProvider>, document.getElementById('app'))