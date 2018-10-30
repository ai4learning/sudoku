import React from 'react'
import { Layout, Icon, Menu, Dropdown, Avatar } from 'antd'
import fetch from '@common/fetch'

export default class Header extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      userInfo: {
      }
    }
  }

  toggle = () => {
    this.props.onCollapse(!this.props.collapsed)
  };

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
      this.props.router.push('/user/login?redirect='+encodeURIComponent(location.href))
    }).catch(() => {
      this.setState({
        userInfo: {}
      })
      this.props.router.push('/user/login?redirect='+encodeURIComponent(location.href))
    })
  }

  componentWillMount() {
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

  render() {
    const menu = (
      <Menu>
        {/* <Menu.Item key='user-center'>
          <a href='/#/user/center'>个人中心</a>
        </Menu.Item>
        <Menu.Divider></Menu.Divider> */}
        <Menu.Item key='logout'>
          <a href='javascript:;' onClick={this.handleLogout.bind(this)}>退出</a>
        </Menu.Item>
      </Menu>
    )

    return (
      <Layout.Header className='header'>
        <Icon className='header_trigger'
          type={this.props.collapsed ? 'menu-unfold' : 'menu-fold'}
          onClick={this.toggle}
        />
        <div class='header_right'>
          <div className='header_user'>
            {
              this.state.userInfo.userId ?
                <Dropdown overlay={menu} trigger={['hover']}>
                  <div>
                    <Avatar icon='user'></Avatar>
                    <span className='header_user_name'>{this.state.userInfo.userId}</span>
                    {/* <Icon type='down'></Icon> */}
                  </div>
                </Dropdown>
                : null
            }
          </div>
        </div>
      </Layout.Header>
    )
  }
}

window.Header = Header