import React, {Component} from 'react'
import {Button, Pagination} from 'antd'
import fetch from '@common/fetch'
import {clearSpellingForAudio, addClass, removeClass, hasClass} from '@common/util'

export default class Wrong extends Component {
  constructor(props) {
    super(props)

    this.state = {
      list: [],
      start: 0,
      limit: 20,
      orderType: '1,2,3',
      total: 0
    }
  }

  componentWillMount() {
    fetch({
      url: '/api/Ajax/GetErrorBookCount?orderType='+this.state.orderType,
      method: 'get',
      type: 'json'
    }).then(result => {
      this.setState({
        total: result.totalNbr
      })
    })
    this.getData(this.state.start, this.state.limit)
  }

  getData(start, limit) {
    fetch({
      url: '/api/Ajax/AjaxGetErrorBook?orderType='+this.state.orderType+'&start='+start+'&limit='+limit,
      method: 'get',
      type: 'json'
    }).then(result => {
      this.setState({
        list: result.data || [],
        start: start,
        limit: limit
      })
    })
  }

  render() {
    return (
      <div className='word-basis_wrong'>
        <div style={{textAlign:'center', lineHeight:'40px', height:'40px', borderBottom:'dashed 1px #e5e5e5'}}>我的错词本：根据平常的练习把错误的记录在册，并且细分错误程度，以达到更好的复习并记忆。</div>
        <div className='wrong_list'>
          {
            this.state.list.map(item => {
              return (
                <div className='wrong_item' key={item.Id}>
                  <div className='wrong_item_body' ref={'item'+item.Id} onClick={this.handleItemClick.bind(this, item)}>
                    <div className='wrong_item_en'>
                      <p>{item.spelling}</p>
                      <p>{item.soundMarkUs}</p>
                    </div>
                    <div className='wrong_item_ch'>
                      <p>{item.meaning}</p>
                    </div>
                  </div>
                  <div className='wrong_item_sound'>
                    <img src="/static/image/volume-small-green.png" onClick={this.handleAudioClick.bind(this, item)}/>
                    <span>
                      <audio ref={'audio'+item.Id}>
                        <source type="audio/mpeg" src={"/soundfile/" + item.spelling.substr(0, 1).toUpperCase() + "/" + clearSpellingForAudio(item.spelling) + ".mp3"}></source>
                      </audio>
                    </span>
                  </div>
                </div>
              )
            })
          }
        </div>
        <div className='wrong_pagination'>
          <Pagination onChange={this.handlePageChange.bind(this)} current={this.state.start+1} total={this.state.total} pageSize={this.state.limit}></Pagination>
        </div>
        <div className='wrong_actions'>
          <Button className='wrong-learn-btn' type='primary' onClick={this.handleWrongClick.bind(this)}>错词学习</Button>
          <Button className='wrong-test-btn' type='primary' onClick={this.handleTodayClick.bind(this)}>当日强化测</Button>
        </div>
      </div>
    )
  }

  handleItemClick(item) {
    let itemObj = this.refs['item'+item.Id]
    if(hasClass(itemObj, 'flippy')) {
      removeClass(itemObj, 'flippy')
    } else {
      addClass(itemObj, 'flippy')
    }
    this.handleAudioClick(item)
  }

  handleAudioClick(item) {
    this.refs['audio'+item.Id].play()
  }

  handlePageChange(page, pageSize) {
    this.getData(page-1, pageSize)
  }

  handleWrongClick() {
    Ha()
  }

  handleTodayClick() {
    window.open('/#/user/test/self?testArea=2&questionNbr=10&questionTypes=0,1,2,3')
  }
}