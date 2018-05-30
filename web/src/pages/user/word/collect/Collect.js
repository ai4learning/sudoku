import React, { Component } from 'react'
import {Pagination} from 'antd'
import Panel from '@components/Panel'
import fetch from '@common/fetch'
import {clearSpellingForAudio, addClass, removeClass, hasClass} from '@common/util'

export default class Collect extends Component {
  constructor(props) {
    super(props)

    this.state = {
      list: [],
      start: 0,
      limit: 20,
      orderType: '0,1,2,3',
      total: 0
    }
  }

  componentWillMount() {
    fetch({
      url: '/api/Ajax/GetVocabularyCount?orderType='+this.state.orderType,
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
      url: '/api/Ajax/GetVocabulary?orderType='+this.state.orderType+'&start='+start+'&limit='+this.state.limit,
      method: 'get',
      type: 'json'
    }).then(result => {
      this.setState({
        list: result.data,
        start,
        limit
      })
    })
  }

  render() {
    return (
      <div className='collect'>
        <Panel title='我的收藏'>
          <div className='collect_list'>
          {
            this.state.list.map(item => {
              return (
                <div className='collect_item' key={item.Id}>
                  <div className='collect_item_body' ref={'item'+item.vocCode} onClick={this.handleItemClick.bind(this, item)}>
                    <div className='collect_item_en'>
                      <p>{item.spelling}</p>
                      <p>{item.soundMarkUs}</p>
                    </div>
                    <div className='collect_item_ch'>
                      <p>{item.meaning}</p>
                    </div>
                  </div>
                  <div className='collect_item_sound'>
                    <img src="/static/image/volume-small-green.png" onClick={this.handleAudioClick.bind(this, item)}/>
                    <span>
                      <audio ref={'audio'+item.vocCode}>
                        <source type="audio/mpeg" src={"/soundfile/" + item.spelling.substr(0, 1).toUpperCase() + "/" + clearSpellingForAudio(item.spelling) + ".mp3"}></source>
                      </audio>
                    </span>                    
                  </div>
                </div>
              )
            })
          }
          </div>
          <div className='collect_pagination'>
            <Pagination onChange={this.handlePageChange.bind(this)} current={this.state.start+1} total={this.state.total} pageSize={this.state.limit}></Pagination>
          </div>
          <div className='collect_actions'></div>
        </Panel>
      </div>
    )
  }

  handlePageChange(page, pageSize) {
    this.getData(page, pageSize)
  }

  handleItemClick(item) {
    let itemObj = this.refs['item'+item.vocCode]
    if(hasClass(itemObj, 'flippy')) {
      removeClass(itemObj, 'flippy')
    } else {
      addClass(itemObj, 'flippy')
    }
    this.handleAudioClick(item)
  }

  handleAudioClick(item) {
    this.refs['audio'+item.vocCode].play()
  }
}