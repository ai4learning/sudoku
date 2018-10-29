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
          <Menu.Item key="/teacher/class">
            <Link to='/teacher/class'>
              <Icon type="solution" />
              <span>班级管理</span>
            </Link>
          </Menu.Item>
          <Menu.Item key="/teacher/student">
            <Link to='/teacher/student'>
              <Icon type="team" />
              <span>学生管理</span>
            </Link>
          </Menu.Item>
          <Menu.Item key="/teacher/course">
            <Link to='/teacher/course'>
              <Icon type="book" />
              <span>课程管理</span>
            </Link>
          </Menu.Item>
        </Menu>
      </Layout.Sider>
    )
  }
}