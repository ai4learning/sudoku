#### AjaxGetUserBook(获取该用户的课程信息)
```
{
  books: [
    {
      Id: 5,
      bookName: '零基础入门拼读',
      currentPosition: {},
      moduleCode: 'xxxx',
      outDate: false,
      totalUnitNbr: 17
    }
  ]
}
```
#### AjaxLoadCourse?moduleCode=xxx(获取某个课程的信息)
```
{
  books: [
    {
      Id: 5,
      bookName: '零基础入门拼读',
      CourseUnits: [
        Id: 38,
        IsFinished: 2,
        IsTested: 1,
        Unit: 'Unit1'
      ],
      ...
    }
  ]
}
```

#### AjaxGetUnit?moduleCode=xxx&unit=x(获取某个课程某个单元的信息)
```
{
  data: [],     // 单词库信息
  studyPos: {}, // 当前学习进度
  ...
}
```

####

#### AjaxInterruptSaveUnit(某个单元未学习完成，保存操作)

#### AjaxFinishSaveUnit(某个单元学习完成，保存操作)

#### AjaxGetErrorUnit(获取历史出错单词单元)

#### AjaxErrorStudySave(历史出错单元，保存操作)