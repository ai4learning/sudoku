import React, {Component} from 'react'
import Antd, {Menu, Avatar, Icon, Dropdown} from 'antd'
import {Link} from 'react-router'
import fetch from '@common/fetch'

export default class Header extends Component {
  constructor(props) {
    super(props)

    this.state = {
      current: this.props.location.pathname,
      userInfo: {}
    }
  }

  componentWillMount() {
    this.getUserInfo()
  }

  getUserInfo() {
    fetch({
      url: '/api/Ajax/AjaxGetUserInfo',
      method: 'POST',
      type: 'json',
      noTip: true
    }).then(data => {
      this.setState({
        userInfo: data
      })
    })
  }

  componentWillReceiveProps(nextProps) {
    this.setState({
      current: nextProps.location.pathname
    })
    if(nextProps.location.pathname == '/user/login' || nextProps.location.pathname == '/user') {
      this.getUserInfo()
    }
  }

  handleClick(e) {
    this.setState({
      current: e.key
    })
  }

  handleLogout() {
    fetch({
      url: '/login/logout',
      method: 'post',
      type: 'json',
      noTip: true
    }).then(() => {
      this.setState({
        userInfo: {}
      })
      this.props.router.push('/user/login')
    }).catch(() => {
      this.setState({
        userInfo: {}
      })
      this.props.router.push('/user/login')
    })
  }

  render() {
    const menu = (
      <Menu>
        <Menu.Item key='user-center'>
          <a href='/#/user/center'>个人中心</a>
        </Menu.Item>
        <Menu.Divider></Menu.Divider>
        <Menu.Item key='logout'>
          <a href='javascript:;' onClick={this.handleLogout.bind(this)}>退出</a>
        </Menu.Item>
      </Menu>
    )

    return (
      <div className='header'>
        <div className='header_container'>
          <a href='/' className='header_logo'>
            {/* <img className='header_logo_img' src='../../static/image/logo_v4.png'/> */}
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
          <div className='header_user'>
            {
              this.state.userInfo.userId ?
                <Dropdown overlay={menu} trigger={['click']}>
                  <div>
                    <Avatar icon='user'></Avatar>
                    <span className='header_user_name'>{this.state.userInfo.userId}</span>
                    <Icon type='down'></Icon>
                  </div>
                </Dropdown>
              : null
            }
          </div>
        </div>
      </div>
    )
  }
}