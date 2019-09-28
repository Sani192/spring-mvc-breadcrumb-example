# Welcome to GitHub

Welcome to GitHub—where millions of developers work together on software. Ready to get started? Let’s learn how this all works by building and publishing your first GitHub Pages website!

## Repositories

Right now, we’re in your first GitHub **repository**. A repository is like a folder or storage space for your project. Your project's repository contains all its files such as code, documentation, images, and more. It also tracks every change that you—or your collaborators—make to each file, so you can always go back to previous versions of your project if you make any mistakes.

This repository contains three important files: The HTML code for your first website on GitHub, the CSS stylesheet that decorates your website with colors and fonts, and the **README** file. It also contains an image folder, with one image file.

## Describe your project

This project is made using spring mvc structure. For dependency management I have used maven tool and for database operation jdbc template. Basically this project is all about dynamic breadcrumb.

You will able to learn how to implement dynamic breadcrumb using jsp taglib, I have created custom jsp taglib for that.

**There are 2 ways defined to display breadcrumb in jsp:**

1). Using static java hashmap: You will create simple hashmap with all url mapped. This map will be initialised once whenever any url hits then will look into map to check. This way it will list of breadcrumb items

2). Using database operation: You will create one database table "breadcrumb-details" and will add all possible number of url.
For example,
1 record for Homepage (fk_parent_breadcrumb_id will be null),
1 record for About Us (fk_parent_breadcrumb_id will be id of homepage if you want to display breadcrumb like this Home then About Us)

Ofcourse, you can customize, you can give your own separator, own css class, own inline style tag.

**How to Use**

1. Include custom breadcrumb taglib in jsp
For example: 
<%@ taglib prefix="b" uri="/WEB-INF/breadcrumbTag.tld"%>

2. write below line at the place where you want to display breadcrumb:
For example:
<b:breadcrumb separator=" > " cssStyle="text-transform: uppercase; text-decoration: none;" cssClass="breadcrumb" />

Attributes separator, cssStyle and cssClass are optional. so you can write in following ways too:
<b:breadcrumb />
then by default separator will be " -> ".

Here,
Separator means text which will be used differentiate between 2 navigation.


## Extra Credit: Keep on building!

Change the placeholder Octocat gif on your GitHub Pages website by [creating your own personal Octocat emoji](https://myoctocat.com/build-your-octocat/) or [choose a different Octocat gif from our logo library here](https://octodex.github.com/). Add that image to line 12 of your `index.html` file, in place of the `<img src=` link.

Want to add even more code and fun styles to your GitHub Pages website? [Follow these instructions](https://github.com/github/personal-website) to build a fully-fledged static website.

![octocat](./images/create-octocat.png)

## Everything you need to know about GitHub

Getting started is the hardest part. If there’s anything you’d like to know as you get started with GitHub, try searching [GitHub Help](https://help.github.com). Our documentation has tutorials on everything from changing your repository settings to configuring GitHub from your command line.
