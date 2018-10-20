import React from 'react'
import { Layout } from 'antd'
import Sider from './Sider.js'
import Header from './Header.js'

export default class TeacherLayout extends React.Component {
  constructor(props) {
    super(props);
  }

  state = {
    collapsed: false
  };

  handleMenuCollapse = (collapsed) => {
    this.setState({
      collapsed
    })
  };

  getMainLayoutStyle = () => {
    return {
      paddingLeft: this.state.collapsed ? '80px' : '200px'
    }
  };

  render() {
    return (
      <Layout className='teacher-layout'>
        <Sider
          {...this.props}
          collapsed={this.state.collapsed}
        >
        </Sider>
        <Layout className='main' style={{ ...this.getMainLayoutStyle() }}>
          <Header
            {...this.props}
            onCollapse={this.handleMenuCollapse}
            collapsed={this.state.collapsed}
          >
          </Header>
          <Layout.Content className='content'>
            {this.props.children || ''}
          </Layout.Content>
        </Layout>
      </Layout>
    )
  }
}