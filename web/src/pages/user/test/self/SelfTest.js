import React, { Component } from 'react'
import { Spin } from 'antd'
import Panel from '@components/Panel'
import fetch from '@common/fetch'
import {formatTime, getQueryString} from '@common/util'
import TestPaper from '../components/TestPaper'

export default class SelfTest extends Component {
  constructor(props) {
    super(props)

    this.state = {
      isReady: false,
      dataCh2En: [],
      dataEn2Ch: [],
      dataListen2Ch: [],
      dataListen2Write: [],
      usedTime: 0
    }

    this.map = {
      0: {
        title: '生词测试',
        type: 5
      },
      1: {
        title: '熟词测试',
        type: 5
      },
      2: {
        title: '当日强化测',
        type: 8
      }
    }

    this.timer = null
    this.testArea = getQueryString('testArea')
  }

  componentWillMount() {
    fetch({
      url: '/api/Ajax/GetExam' + location.search,
      method: 'get',
      type: 'json'
    }).then(result => {
      this.setState({
        isReady: true,
        dataCh2En: result.dataCh2En || [],
        dataEn2Ch: result.dataEn2Ch || [],
        dataListen2Ch: result.dataListen2Ch || [],
        dataListen2Write: result.dataListen2Write || []
      })
      this.timer = setInterval(() => {
        this.setState({
          usedTime: ++this.state.usedTime
        })
      }, 1000)
    })
  }

  render() {
    return (
      <div className='page_test_self'>
        <Panel title={(this.map[this.testArea]||{}).title||'测试'} loading={!this.state.isReady} extra={'已用时：'+formatTime(this.state.usedTime)}>
          <TestPaper {...this.state} onSubmit={this.handleSubmit.bind(this)}></TestPaper>
        </Panel>
      </div>
    )
  }

  handleSubmit(score) {
    clearInterval(this.timer)
    fetch({
      url: '/api/Ajax/AjaxSaveMutiTest',
      method: 'post',
      data: {
        resultScore: score,
        testType: (this.map[this.testArea]||{}).type || 5,
        realDuration: this.state.usedTime
      }
    })
  }
}