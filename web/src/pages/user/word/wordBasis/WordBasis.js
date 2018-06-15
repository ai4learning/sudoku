import React, {Component} from 'react'
import Antd, {Tabs, Icon} from 'antd'
import Panel from '@components/Panel'
import Course from './components/Course'
import Wrong from './components/Wrong'
import Review from './components/Review'

export default class WordBasis extends Component {
  constructor(props) {
    super(props)
  }

  render() {
    return (
      <div className='word-basis'>
        <Panel title='单词基础'>
          <Tabs type='card'>
            <Tabs.TabPane tab={<span><Icon type='profile'/>我的课程</span>} key='course'>
              <Course></Course>
            </Tabs.TabPane>
            <Tabs.TabPane tab={<span><Icon type='calendar'/>我的错词本</span>} key='wrong'>
              <Wrong></Wrong>
            </Tabs.TabPane>
            <Tabs.TabPane tab={<span><Icon type='file-text'/>智能复习</span>} key='review'>
              <Review></Review>
            </Tabs.TabPane>
          </Tabs>
        </Panel>
      </div>
    )
  }
}