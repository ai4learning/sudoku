import React, {Component} from 'react'
import ReactDOM from 'react-dom'
import {Router, hashHistory} from 'react-router'
import routes from './routes'
import 'normalize.css'
import 'antd/dist/antd.css'
import './style/index.less'

ReactDOM.render(
  <Router history={hashHistory}>
    {routes}
  </Router>, document.getElementById('app'))