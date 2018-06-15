import React, {Component} from 'react'
import {Spin} from 'antd'

export default class Panel extends Component {
  constructor(props) {
    super(props)
  }

  render() {
    return (
      <div className='panel'>
        {this.props.title && <div className='panel_header'>{this.props.title}</div>}
        {this.props.extra && <div className='panel_extra'>{this.props.extra}</div>}
        <Spin size='large' spinning={!!this.props.loading} tip='Loading...'>
          {this.props.children}
        </Spin>
      </div>
    )
  }
}