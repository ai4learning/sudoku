import React, {Component} from 'react'
import Header from './Header.js'
import Footer from './Footer.js'

export default class Layout extends Component {
  constructor(props) {
    super(props)
  }

  render() {
    return (
      <div className='layout'>
        <Header {...this.props}></Header>
        <div className='content'>
          {this.props.children || ''}
        </div>
        <Footer></Footer>
      </div>
    )
  }
}