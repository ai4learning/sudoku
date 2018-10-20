import React from 'react'
import { Layout, Menu, Icon } from 'antd'
import {Link} from 'react-router'
import logo from '@src/assets/logo.png'

export default class Sider extends React.Component {
  constructor(props) {
    super(props);
  }

  state = {
    current: this.props.location.pathname
  };

  componentWillReceiveProps(nextProps) {
    this.setState({
      current: nextProps.location.pathname
    });
  }

  render() {
    return (
      <Layout.Sider className='sider'
        trigger={null}
        collapsible
        collapsed={this.props.collapsed}
        width={200}
      >
        <div className='logo-bar'>
          <img src={logo} />
          <h1>教师端</h1>
        </div>
        <Menu theme="dark" mode="inline" selectedKeys={[this.state.current]}>
          <Menu.Item key="/resource">
            <Link to='/resource'>
              <Icon type="file-text" />
              <span>班级管理</span>
            </Link>
          </Menu.Item>
          <Menu.Item key="/potential">
            <Link to='/potential'>
              <Icon type="team" />
              <span>学生管理</span>
            </Link>
          </Menu.Item>
          <Menu.Item key="/feedback">
            <Icon type="clock-circle" />
            <span>课程分配</span>
          </Menu.Item>
        </Menu>
      </Layout.Sider>
    )
  }
}