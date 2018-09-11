import React, {Component} from 'react'
import ReactDOM from 'react-dom'
import {Router} from 'react-router'
import routes from './routes'
import history from './history'
import 'normalize.css'
import './style/index.less'

ReactDOM.render(
  <Router history={history}>
    {routes}
  </Router>, document.getElementById('app'))