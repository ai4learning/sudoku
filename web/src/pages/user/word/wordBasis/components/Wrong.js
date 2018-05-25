import React, {Component} from 'react'
import {Button} from 'antd'

export default class Wrong extends Component {
  constructor(props) {
    super(props)
  }

  render() {
    return (
      <div className='word-basis_wrong'>
        <Button type='primary' onClick={this.handleWrongClick.bind(this)}>错词学习</Button>
        <Button type='primary' onClick={this.handleTodayClick.bind(this)}>当日强化测</Button>
      </div>
    )
  }

  handleWrongClick() {
    Ha()
  }

  handleTodayClick() {
    window.open('/user/test/self?testArea=2&questionNbr=10&questionTypes=0,1,2,3')
  }
}