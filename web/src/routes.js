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
import TeacherClass from './pages/teacher/class/Class'
import TeacherStudent from './pages/teacher/student/Student'
import TeacherCourse from './pages/teacher/course/Course'
import TeacherWord from './pages/teacher/course/Word'
import TeacherRank from './pages/teacher/rank/Rank'

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
        <IndexRoute component={TeacherClass}></IndexRoute>
        <Route path='class' component={TeacherClass}></Route>
        <Route path='student' component={TeacherStudent}></Route>
        <Route path='course' component={TeacherCourse}></Route>
        <Route path='word' component={TeacherWord}></Route>
        <Route path='rank' component={TeacherRank}></Route>
      </Route>
      <IndexRoute component={Home}>
      </IndexRoute>
    </Route>
)