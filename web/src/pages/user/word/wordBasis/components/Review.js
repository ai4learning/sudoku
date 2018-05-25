import React, { Component } from 'react'
import {Button} from 'antd'

export default class Review extends Component {
  constructor(props) {
    super(props)
  }

  render() {
    return (
      <div className='word-basis_review'>
        <Button type='primary' onClick={this.handleClick.bind(this)}>开始复习吧</Button>
      </div>
    )
  }

  handleClick() {
    Ua(false)
  }
}