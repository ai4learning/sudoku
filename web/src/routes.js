import React from 'react'
import {Router, Route, IndexRoute, Redirect, browserHistory} from 'react-router'
import App from './App.js'
import UserLayout from './layout/user/Layout'
import AdminLayout from './layout/admin/Layout'
import Home from './pages/Home'
import Word from './pages/user/word/Word'
import UserLogin from './pages/user/login/Login'
import Center from './pages/user/center/Center'
import Rank from './pages/user/rank/Rank'
import UnitTest from './pages/user/test/unit/UnitTest'
import SelfTest from './pages/user/test/self/SelfTest'
import TestResults from './pages/user/test/results/TestResults'

import TeacherLayout from './layout/teacher/Layout'
import TeacherLogin from './pages/teacher/login/Login'

export default (
    <Route path='/' component={App}>
      <Route path='user' component={UserLayout}>
        <IndexRoute component={Word}></IndexRoute>
        <Route path='login' component={UserLogin}></Route>
        <Route path='word' component={Word}></Route>
        <Route path='center' component={Center}></Route>
        <Route path='rank' component={Rank}></Route>
        <Route path='test/unit' component={UnitTest}></Route>
        <Route path='test/self' component={SelfTest}></Route>
        <Route path='test/results' component={TestResults}></Route>
      </Route>
      <Route path='admin' component={AdminLayout}>
        <IndexRoute component={Word}></IndexRoute>
      </Route>
      <Route path='teacher' component={TeacherLayout}>
        <Route path='login' component={TeacherLogin}></Route>
      </Route>
      <IndexRoute component={Home}>
      </IndexRoute>
    </Route>
)