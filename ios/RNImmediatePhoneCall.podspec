require 'json'

package = JSON.parse(File.read(File.join(__dir__, '../package.json')))

Pod::Spec.new do |s|
  s.name         = "RNImmediatePhoneCall"
  s.version      = package['version']
  s.summary      = "RNImmediatePhoneCall"
  s.description  = package['description']
  s.homepage     = package['homepage']
  s.license      = package['license']
  s.author       = package['author']
  s.source       = { :git => package['repository']['url'], :tag => "v#{s.version}" }
  s.platform     = :ios, '10.0'
  s.source_files = "RNImmediatePhoneCall/**/*.{h,m}"
  s.requires_arc = true

  s.dependency 'React'
end

  
