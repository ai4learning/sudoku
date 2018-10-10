import React, {Component} from 'react'
import {Button} from 'antd'
import Panel from '@components/Panel'
import fetch from '@common/fetch'

export default class MemoryCurve extends Component {
  constructor(props) {
    super(props)

    this.state = {
      count: 0
    }
  }

  componentWillMount() {
    fetch({
      url: '/api/Ajax/AjaxGetReviewCount',
      method: 'get',
      type: 'json'
    }).then(result => {
      this.setState({
        count: result.totalNbr
      })
    })
  }

  render() {
    return (
      <div className='memory-curve'>
        <Panel title='记忆曲线'>
          <div style={{textAlign:'center'}}>
            <div style={{lineHeight:'40px', height:'40px', borderBottom:'dashed 1px #e5e5e5'}}>智能复习以德国心理学家赫尔曼·艾宾浩斯(Hermann Ebbinghaus)的遗忘曲线为理论基础，辅助记忆。</div>
            <img src="/static/image/ebbing.png" />
          </div>
          <div style={{textAlign:'center'}}>
            <Button onClick={this.handleClick.bind(this)} type='primary'>复习一下</Button>
          </div>
          <div style={{ paddingLeft: '280px', textAlign: 'center', fontSize: '20px', lineHeight: '40px' }}>
            你约有<span style={{fontSize: '30px', color: 'red'}}>{this.state.count}</span>个单词需要复习
          </div>
        </Panel>
      </div>
    )
  }

  handleClick() {
    Ua()
  }
}