import React, {Component} from 'react'
import Antd, {Menu, Icon, Tabs} from 'antd'
import WordBasis from './wordBasis/WordBasis'
import TestSelf from './testSelf/TestSelf'
import MemoryCurve from './memoryCurve/MemoryCurve'
import Report from './report/Report'
import Collect from './collect/Collect'
import WordSearch from './search/WordSearch'

export default class Word extends Component {
  constructor(props) {
    super(props)
    document.title = '单词学习'
  }

  render() {
    return (
      <div className='page_learning_word'>
        <Tabs tabPosition='left' size='large'>
          <Tabs.TabPane key='word' tab={<span><Icon type='book'/>单词基础</span>}>
            <WordBasis></WordBasis>
          </Tabs.TabPane>
          <Tabs.TabPane key='test' tab={<span><Icon type='exclamation-circle-o'/>自主测试</span>}>
            <TestSelf></TestSelf>
          </Tabs.TabPane>
          {/* <Tabs.TabPane key='curve' tab={<span><Icon type='line-chart'/>记忆曲线</span>}>
            <MemoryCurve></MemoryCurve>
          </Tabs.TabPane> */}
          <Tabs.TabPane key='report' tab={<span><Icon type='bar-chart'/>学习报告</span>}>
            <Report></Report>
          </Tabs.TabPane>
          <Tabs.TabPane key='collect' tab={<span><Icon type='star-o'/>我的收藏</span>}>
            <Collect></Collect>
          </Tabs.TabPane>
          <Tabs.TabPane key='search' tab={<span><Icon type='search' />单词搜索</span>}>
            <WordSearch></WordSearch>
          </Tabs.TabPane>
        </Tabs>
      </div>
    )
  }
}