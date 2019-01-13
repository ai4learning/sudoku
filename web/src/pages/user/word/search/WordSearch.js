import React from 'react'
import {Select, Card, Spin, Icon} from 'antd'
import Panel from '@src/components/Panel'
import fetch from '@common/fetch'
import debounce from 'lodash/debounce'
import { clearSpellingForAudio } from '@common/util'

export default class WordSearch extends React.PureComponent {
  constructor(props) {
    super(props)
    this.handleSearch = debounce(this.handleSearch, 500)
  }

  state = {
    data: [],
    value: undefined,
    fetching: false,
    visible: false,
    wordData: {}
  }

  handleChange = (value) => {
    this.setState({
      value: value,
      data: [],
      fetching: false
    })
  }

  handleSearch = (value) => {
    this.setState({
      data: [],
      fetching: true,
      visible: false
    })
    
    fetch({
      url: '/api/Ajax/AjaxGetSimilarWords',
      method: 'get',
      type: 'json',
      data: {
        keyword: value
      }
    }).then(data => {
      this.setState({
        data: data.data,
        fetching: false
      })
    })
  }

  handleSelect = (value) => {
    if(value != this.state.value) {
      fetch({
        url: '/api/Ajax/AjaxGetWord',
        method: 'get',
        type: 'json',
        data: {
          keyword: value
        }
      }).then(data => {
        this.setState({
          wordData: data,
          visible: true
        })
      })
    }
  }

  handleAudioClick = () => {
    this.refs['audio' + this.state.wordData.id].play()
  }

  render() {
    return (
      <div className='search'>
        <Panel title='单词搜索'>
          <div style={{textAlign: 'center'}}>
            <Select
              style={{width: '600px', marginTop: '20px'}}
              showSearch
              size='large'
              placeholder='请输入单词'
              filterOption={false}
              suffixIcon={<Icon type='search' style={{ color: '#1890ff', fontSize: '22px', position: 'relative', left: '2px', top: '-4px'}} />}
              value={this.state.value}
              notFoundContent={this.state.fetching ? <span><Spin size='small' />搜索中...</span> : null}
              onChange={this.handleChange}
              onSearch={this.handleSearch}
              onSelect={this.handleSelect}
              >
              {
                (this.state.data||[]).map(item => {
                  return <Select.Option key={item.spelling} value={item.spelling}>{item.spelling} {item.meaning}</Select.Option>
                })
              }
            </Select>
          </div>
          {
            this.state.visible ? 
              <div style={{ width: '600px', margin: '20px auto' }}>
                <div>
                  <span style={{ fontSize: '40px', color: '#f60' }}>{this.state.wordData.spelling}</span>
                  {/* <Icon type='star' style={{fontSize: '24px', paddingLeft: '10px', cursor: 'pointer'}}/> */}
                </div>
                <div>
                  <span style={{fontSize: '20px'}}>读音：</span>
                  <span>{this.state.wordData.soundmark}</span>
                  <img alt="发音" title="发音" src="/content/img/volume-medium.png" height="20px" style={{ cursor: 'pointer', paddingLeft: '10px' }} onClick={this.handleAudioClick}></img>
                  <span>
                    <audio ref={'audio' + this.state.wordData.id}>
                      <source type="audio/mpeg" src={"/soundfile/" + this.state.wordData.spelling.substr(0, 1).toUpperCase() + "/" + clearSpellingForAudio(this.state.wordData.spelling) + ".mp3"}></source>
                    </audio>
                  </span>
                </div>
                <div><span style={{fontSize: '20px'}}>释义：</span>{this.state.wordData.meaning}</div>
                <div><span style={{fontSize: '20px'}}>来源：</span>{this.state.wordData.bookName} - {this.state.wordData.unit}</div>
              </div> : null
          }
        </Panel>
      </div>
    )
  }
}