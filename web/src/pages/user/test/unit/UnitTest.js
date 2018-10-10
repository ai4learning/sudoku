import React, {Component} from 'react'
import {Collapse} from 'antd'
import Panel from '@components/Panel'
import fetch from '@common/fetch'
import { getQueryString, formatTime } from '@common/util'
import TestPaper from '../components/TestPaper'

export default class UnitTest extends Component {
  constructor(props) {
    super(props)
    document.title = '单元测试'
    
    this.state = {
      isReady: false,
      dataEn2Ch: [],
      dataCh2En: [],
      dataListen2Ch: [],
      dataListen2Write: [],
      usedTime: 0
    }

    this.timer = null
  }

  componentWillMount() {
    fetch({
      url: '/api/Ajax/GetUnitExam' + this.props.location.search,
      method: 'get',
      type: 'json'
    }).then(result => {
      this.setState({
        isReady: true,
        dataEn2Ch: result.data.dataEn2Ch || [],
        dataCh2En: result.data.dataCh2En || [],
        dataListen2Ch: result.data.dataListen2Ch || [],
        dataListen2Write: result.data.dataListen2Write || []
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
      <div className='page_test_unit'>
        <Panel title='单元测试' loading={!this.state.isReady} extra={'已用时：' + formatTime(this.state.usedTime)}>
          <TestPaper {...this.state} onSubmit={this.handleSubmit.bind(this)}>
          </TestPaper>
        </Panel>
      </div>
    )
  }

  handleSubmit(score) {
    clearInterval(this.timer)
    fetch({
      url: '/api/Ajax/AjaxSaveMutiTest',
      method: 'post',
      type: 'json',
      data: {
        moduleCode: this.props.location.query.moduleCode,
        unitNbr: this.props.location.query.unitNbr,
        resultScore: score,
        testType: 1,
        realDuration: this.state.usedTime
      }
    })
  }
}