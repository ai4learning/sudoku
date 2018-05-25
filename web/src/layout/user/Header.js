import React, {Component} from 'react'
import Antd, {Menu} from 'antd'
import {Link} from 'react-router'

export default class Header extends Component {
  constructor(props) {
    super(props)

    this.state = {
      current: window.decodeURI(window.location.pathname)
    }
  }

  handleClick(e) {
    this.setState({
      current: e.key
    })
  }

  render() {
    return (
      <div className='header'>
        <div className='header_container'>
          <a href='/' className='header_logo'>
            <img className='header_logo_img' src='../../static/image/logo_v4.png'/>
            九宫格单词训练营
          </a>
          <div className='header_menu'>
            <Menu
              selectedKeys={[this.state.current]}
              onClick={this.handleClick.bind(this)}
              theme='dark'
              mode='horizontal'
              style={{lineHeight: '64px'}}>
              <Menu.Item key='home' key='/user'><Link to='/user'>首页</Link></Menu.Item>
              <Menu.Item key='user' key='/user/center'><Link to='/user/center'>用户中心</Link></Menu.Item>
              <Menu.Item key='rank' key='/user/rank'><Link to='/user/rank'>排行榜</Link></Menu.Item>
            </Menu>
          </div>
        </div>
      </div>
    )
  }
}