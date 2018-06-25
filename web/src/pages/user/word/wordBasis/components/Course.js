/**
 * 我的课程
 */
import React, {Component} from 'react'
import Antd, { Card, Breadcrumb, Button, Progress, Alert } from 'antd'
import fetch from '@common/fetch'

export default class Course extends Component {
  constructor(props) {
    super(props)
    this.state = {
      books: [],
      currentBook: null
    }
  }

  componentWillMount() {
    this.getUserBook()
  }

  getUserBook() {
    fetch({
      url: '/api/Ajax/AjaxGetUserBook',
      method: 'post',
      type: 'json'
    }).then(data => {
      this.setState({
        books: data.books || []
      })
    })
  }

  render() {
    return (
      <div className='word-basis_course'>
        <div className='course_navigation'>
          <Breadcrumb separator='>'>
            <Breadcrumb.Item onClick={this.handleClick.bind(this)}>
              <a style={{color: '#1890ff', fontSize: '16px', fontWeight: 'bold', textDecoration: 'underline'}}href='javascript:;'>所有课程</a>
            </Breadcrumb.Item>
            {
              this.state.currentBook ? <Breadcrumb.Item style={{fontWeight: 'bold'}}>{this.state.currentBook.bookName}</Breadcrumb.Item> : null
            }
          </Breadcrumb>
        </div>
        <div className='course_list' style={{ display: this.state.currentBook ? 'none' : 'flex' }}>
          {
            this.state.books.map(item => {
              return (
                <div key={item.bookName} className='course_item'>
                  <Card  title={item.bookName} hoverable onClick={this.handleBookClick.bind(this, item)}>
                    <div>总单元数：{item.totalUnitNbr}</div>
                    <div>当前学习到：{(item.currentPosition||{}).spelling}</div>
                    <div style={{display: 'flex'}}>
                      <div>当前进度：</div>
                      <div style={{flex: 1}}>
                        <Progress percent={parseInt(((item.currentPosition||{}).unitNbr||0)/item.totalUnitNbr*100)}></Progress>
                      </div>
                    </div>
                  </Card>
                </div>
              )
            })
          }
        </div>
        <div className='unit_list' style={{ display: this.state.currentBook ? 'flex' : 'none' }}>
          {
            this.state.currentBook ? (this.state.currentBook.CourseUnits || []).map((item,index) => {
              return (
                <div key={item.Id} className='unit_item'>
                  <Card title={item.Unit||'Unit'+(index+1)} hoverable>
                    <div className='unit_item_body'>
                      <div>
                        <div>
                          单词学习：<span style={{ color: item.IsFinished == 2 ? '#67B239' : item.IsFinished == 0 ? 'orange' : 'inhret'}}
                          >{item.IsFinished == 2 ? '已完成' : item.IsFinished == 0 ? '未完成' : '未开始'}</span>
                        </div>
                        <div>
                          听读训练：<span style={{ color: item.IsFinished == 2 ? '#67B239' : item.IsFinished == -1 ? 'inhret' : 'inhret'}}
                          >{item.IsFinished == 2 ? '已完成' : item.IsFinished == -1 ? '未开启' : '未开始'}</span>
                        </div>
                        <div>
                          单元测试：<span style={{ color: item.IsFinished == -1 ? 'inhret' : item.IsTested == 1 ? '#67B239' : item.IsTested == 2 ? 'orange' : item.IsTested == -1 ? 'red' : 'inhret'}}
                          >{item.IsFinished == -1 ? '未开启' : item.IsTested == 1 ? '已满分通过' : item.IsTested == 2 ? '已通过' : item.IsTested == -1 ? '未通过' : '未开始'}</span>
                        </div>
                      </div>
                      <div className='unit_item_btns'>
                        <Button className={item.IsFinished == 2 ? 'finished' : item.IsFinished == 0 ? 'unfinished' : 'unstarted'} onClick={this.handleWordLearnClick.bind(this, item)}>单词学习</Button><br/>
                        <Button disabled={item.IsFinished == -1} className={item.IsFinished == 2 ? 'finished' : 'unstarted'} onClick={this.handleListenReadClick.bind(this, item)}>听读训练</Button><br/>
                        <Button disabled={item.IsFinished == -1} className={item.IsFinished == -1 ? 'unstarted' : item.IsTested == 1 ? 'finished' : item.IsTested == 2 ? 'unfinished' : 'unstarted'} onClick={this.handleUnitTestClick.bind(this, item)}>单元测试</Button>
                        {/* <Button className='unfinished'>词汇应用</Button> */}
                      </div>
                    </div>
                  </Card>
                </div>
              )
            }) : null
          }
        </div>
      </div>
    )
  }

  handleBookClick(item) {
    fetch({
      url: '/api/Ajax/AjaxLoadCourse',
      method: 'get',
      type: 'json',
      data: {
        moduleCode: item.moduleCode
      }
    }).then(data => {
      this.setState({
        currentBook: data.books[0] || {}
      })
    })
  }

  handleClick() {
    this.setState({
      currentBook: null
    })
    this.getUserBook()
  }

  handleWordLearnClick(item) {
    Ka(item.moduleCode + '-' + item.unitNbr)
  }

  handleListenReadClick(item) {
    Cb('sp-' + item.moduleCode + '-' + item.unitNbr)
  }

  handleUnitTestClick(item) {
    window.open('/#/user/test/unit?moduleCode=' + item.moduleCode + '&unitNbr=' + item.unitNbr)
  }

  handleWordUseClick(item) {
    wordUse('s-' + item.moduleCode + '-' + item.unitNbr)
  }
}