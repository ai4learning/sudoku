import React, { Component } from 'react'
import Header from './Header.js'
import Footer from './Footer.js'

export default class UserLayout extends Component {
  constructor(props) {
    super(props)
  }

  render() {
    return (
      <div className='user-layout'>
        <Header {...this.props}></Header>
        <div className='content'>
          {this.props.children || ''}
        </div>
        <Footer></Footer>
      </div>
    )
  }
}